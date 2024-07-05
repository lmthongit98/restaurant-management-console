package com.tma.training.restaurant.infrastructure.persistences.csv.repositories;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, ID> {
    T save(T entity);
    void saveAll(List<T> entities);
    Optional<T> findById(ID id);
    void delete(T entity);
    void deleteById(ID id);
    void deleteAll(List<T> entities);
    List<T> findAll();
}
