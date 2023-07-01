package com.tma.training.restaurant.repository.impl;

import com.tma.training.restaurant.common.utils.CsvFileUtil;
import com.tma.training.restaurant.common.utils.DateUtil;
import com.tma.training.restaurant.entity.CsvDataModel;
import com.tma.training.restaurant.repository.CrudRepository;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SimpleCsvRepository<T extends CsvDataModel> implements CrudRepository<T, String> {

    protected final Map<String, T> data;

    private final Class<T> entityClass;

    public SimpleCsvRepository() {
        Type type = getClass().getGenericSuperclass();
        ParameterizedType paramType = (ParameterizedType) type;
        entityClass = (Class<T>) paramType.getActualTypeArguments()[0];
        data = CsvFileUtil.readFile(entityClass).stream().collect(Collectors.toMap(CsvDataModel::getId, Function.identity(), (existingValue, newValue) -> newValue, LinkedHashMap::new));
    }

    @Override
    public T save(T entity) {
        if (Objects.isNull(entity.getId())) {
            entity.setId(UUID.randomUUID().toString());
            entity.setCreatedDate(DateUtil.getCurrentDate());
        }
        entity.setUpdatedDate(DateUtil.getCurrentDate());
        data.put(entity.getId(), entity);
        saveAll();
        return entity;
    }

    @Override
    public void saveAll(List<T> entities) {
        for (T entity : entities) {
            save(entity);
        }
    }

    @Override
    public Optional<T> findById(String id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public void delete(T entity) {
        data.remove(entity.getId());
        saveAll();
    }

    @Override
    public void deleteAll(List<T> entities) {
        for (T entity : entities) {
            delete(entity);
        }
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(data.values());
    }

    private void saveAll() {
        if (!data.isEmpty()) {
            List<T> records = new ArrayList<>(data.values());
            CsvFileUtil.writeFile(records, entityClass);
        }
    }

}
