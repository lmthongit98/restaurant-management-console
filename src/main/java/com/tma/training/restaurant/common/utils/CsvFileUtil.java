package com.tma.training.restaurant.common.utils;

import com.tma.training.restaurant.common.anotations.Column;
import com.tma.training.restaurant.common.anotations.CsvFile;
import com.tma.training.restaurant.entity.CsvDataModel;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.*;

public class CsvFileUtil {
    private static final String csvDelimiter = ",";

    public static <T> List<T> readFile(Class<T> tClass) {
        String csvFilePath = getCsvFileName(tClass);
        List<T> records = new ArrayList<>();
        ClassLoader classLoader = CsvFileUtil.class.getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(csvFilePath);
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            String[] headers = bufferedReader.readLine().split(csvDelimiter);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] csvFields = line.split(csvDelimiter);
                T record = parseCsvRecord(csvFields, headers, tClass);
                records.add(record);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }

    public static <T extends CsvDataModel> void writeFile(List<T> data, Class<T> tClass) {
        String csvFilePath = getCsvFileName(tClass);
        ClassLoader classLoader = CsvFileUtil.class.getClassLoader();
        URL resourceUrl = classLoader.getResource(csvFilePath);
        if (Objects.isNull(resourceUrl)) {
            return;
        }
        File file = new File(resourceUrl.getFile());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            String header = data.get(0).toCsvHeader();
            writer.write(header);
            writer.newLine();
            for (T record : data) {
                String csvLine = record.toCsvString();
                writer.write(csvLine);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static <T> T parseCsvRecord(String[] csvFields, String[] headers, Class<T> tClass) {
        try {
            T object = tClass.getDeclaredConstructor().newInstance();
            for (int i = 0; i < csvFields.length; i++) {
                String colName = headers[i].trim();
                String colValue = csvFields[i].trim();
                mapValueToField(object, colName, colValue, tClass);
            }
            return object;
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private static <T> void mapValueToField(T object, String colName, String colValue, Class<T> tClass) throws IllegalAccessException {
        Class<?> currentClass = tClass;
        while ((currentClass != null)) {
            Field[] fields = currentClass.getDeclaredFields();
            for (Field field : fields) {
                if (!field.isAnnotationPresent(Column.class)) {
                    continue;
                }
                Column col = field.getAnnotation((Column.class));
                if (!col.name().equals(colName)) {
                    continue;
                }
                field.setAccessible(true);
                field.set(object, DataConvertUtil.convertToFieldType(colValue, field.getType()));
                return;
            }
            currentClass = currentClass.getSuperclass();
        }
    }

    private static String getCsvFileName(Class<?> entityClass) {
        if (!entityClass.isAnnotationPresent(CsvFile.class)) {
            throw new RuntimeException("File name can not be found");
        }
        CsvFile CsvFile = entityClass.getAnnotation(CsvFile.class);
        return CsvFile.name();
    }


}
