package com.tma.training.restaurant.domain.usecases.menu;

import com.tma.training.restaurant.domain.models.MenuModel;

import java.util.List;

public interface GetAllMenusUseCase {
    List<MenuModel> getAllMenus();
}
