package com.tma.training.restaurant.infrastructures.csv.repositories.impl;

import com.tma.training.restaurant.infrastructures.csv.entities.Menu;
import com.tma.training.restaurant.infrastructures.csv.repositories.CsvMenuRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CsvMenuRepositoryImpl extends SimpleCsvRepository<Menu> implements CsvMenuRepository {

    private static CsvMenuRepositoryImpl instance;

    private CsvMenuRepositoryImpl() {
    }

    public static CsvMenuRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new CsvMenuRepositoryImpl();
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
