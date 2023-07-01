package com.tma.training.restaurant.repository.impl;

import com.tma.training.restaurant.entity.Menu;
import com.tma.training.restaurant.repository.MenuRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public List<Menu> findByDeleted(boolean deleted) {
        return findAll().stream().filter(menu -> menu.getDeleted().equals(deleted)).collect(Collectors.toList());
    }

    @Override
    public Optional<Menu> findByIdAndDeleted(String id, boolean deleted) {
        return findById(id).filter(menu -> menu.getDeleted().equals(deleted));
    }
}
