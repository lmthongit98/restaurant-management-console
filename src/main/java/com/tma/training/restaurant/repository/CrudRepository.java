package com.tma.training.restaurant.repository;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, ID> {
    T save(T entity);
    Optional<T> findById(ID id);
    void update(T entity);
    void delete(T entity);
    List<T> findAll();
}
