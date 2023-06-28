package com.tma.training.restaurant.controller;

import com.tma.training.restaurant.dto.MenuDto;

import java.util.List;

public interface BaseController<T> {

    List<T> findAll();

    T findById(String id);

    T create(MenuDto menuDto);

    T update(MenuDto menuDto);


    void delete(String id);
}
