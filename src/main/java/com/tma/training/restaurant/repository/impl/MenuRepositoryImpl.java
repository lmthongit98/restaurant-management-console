package com.tma.training.restaurant.repository.impl;

import com.tma.training.restaurant.entity.Menu;
import com.tma.training.restaurant.repository.MenuRepository;

public class MenuRepositoryImpl extends SimpleCsvRepository<Menu> implements MenuRepository {
    private static MenuRepositoryImpl instance;

    private MenuRepositoryImpl() {
    }

    public static MenuRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new MenuRepositoryImpl();
        }
        return instance;
    }
}
