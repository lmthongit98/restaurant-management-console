package com.tma.training.restaurant.domain.repositories;

import com.tma.training.restaurant.domain.models.MenuModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MenuRepository {
    List<MenuModel> findAll();
    Optional<MenuModel> findById(UUID id);
    void create(MenuModel menuModel);
    void update(MenuModel menuModel);
    void deleteById(UUID id);
    boolean existsById(UUID menuId);
}
