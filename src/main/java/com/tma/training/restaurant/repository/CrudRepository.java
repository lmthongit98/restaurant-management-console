package com.tma.training.restaurant.repository;

import java.util.List;

public interface CrudRepository<T, ID> {
    T save(T entity);
    T findById(ID id);
    void update(T entity);
    void delete(T entity);
    List<T> findAll();
}
