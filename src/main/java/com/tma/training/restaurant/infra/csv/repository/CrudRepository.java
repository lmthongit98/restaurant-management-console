package com.tma.training.restaurant.infra.csv.repository;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, ID> {
    T save(T entity);
    void saveAll(List<T> entities);
    Optional<T> findById(ID id);
    void delete(T entity);
    void deleteAll(List<T> entities);
    List<T> findAll();
}
