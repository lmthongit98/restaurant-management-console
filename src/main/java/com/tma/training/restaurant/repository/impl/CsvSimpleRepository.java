package com.tma.training.restaurant.repository.impl;

import com.tma.training.restaurant.common.anotations.CsvFile;
import com.tma.training.restaurant.common.utils.CsvFileUtil;
import com.tma.training.restaurant.entity.CsvDataModel;
import com.tma.training.restaurant.repository.CrudRepository;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CsvSimpleRepository<T extends CsvDataModel> implements CrudRepository<T, String> {

    private final Class<T> entityClass;
    private final Map<String, T> data;
    private final String csvFileName;

    public CsvSimpleRepository() {
        Type type = getClass().getGenericSuperclass();
        ParameterizedType paramType = (ParameterizedType) type;
        entityClass = (Class<T>) paramType.getActualTypeArguments()[0];
        csvFileName = getCsvFileName(entityClass);
        data = CsvFileUtil.readFile(csvFileName, entityClass).stream().collect(Collectors.toMap(CsvDataModel::getId,  Function.identity()));
    }

    private String getCsvFileName(Class<T> entityClass) {
        if (!entityClass.isAnnotationPresent(CsvFile.class)) {
            throw new RuntimeException("File name can not be found");
        }
        CsvFile CsvFile = entityClass.getAnnotation(CsvFile.class);
        return CsvFile.name();
    }


    @Override
    public T save(T entity) {
        data.put(entity.getId(), entity);
        saveAll();
        return entity;
    }

    @Override
    public T findById(String id) {
        return data.get(id);
    }

    @Override
    public void update(T entity) {
        data.put(entity.getId(), entity);
        saveAll();
    }

    @Override
    public void delete(T entity) {
        data.remove(entity.getId());
        saveAll();
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(data.values());
    }

    private void saveAll() {
        if (!data.isEmpty()) {
            List<T> records = new ArrayList<>(data.values());
            CsvFileUtil.writeFile(csvFileName, records);
        }
    }

}
