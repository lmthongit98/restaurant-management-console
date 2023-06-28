package com.tma.training.restaurant.service;

import java.util.List;

public interface BaseService<T> {
    List<T> findAll();
    T findById(String id);
    T create(T dto);
    T update(T dto);
    void delete(String id);
}
