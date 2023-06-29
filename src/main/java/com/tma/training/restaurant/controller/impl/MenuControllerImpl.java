package com.tma.training.restaurant.controller.impl;

import com.tma.training.restaurant.controller.MenuController;
import com.tma.training.restaurant.dto.MenuDto;
import com.tma.training.restaurant.service.MenuService;
import com.tma.training.restaurant.service.impl.MenuServiceImpl;

import java.util.List;

public class MenuControllerImpl implements MenuController {
    private final MenuService menuService;

    private MenuControllerImpl() {
        menuService = MenuServiceImpl.getInstance();
    }

    private static MenuControllerImpl instance;

    public static MenuControllerImpl getInstance() {
        if (instance == null) {
            instance = new MenuControllerImpl();
        }
        return instance;
    }


    @Override
    public List<MenuDto> findAll() {
        return menuService.findAll();
    }

    @Override
    public MenuDto findById(String id) {
        return menuService.findById(id);
    }

    @Override
    public MenuDto create(MenuDto menuDto) {
        return menuService.create(menuDto);
    }

    @Override
    public MenuDto update(MenuDto menuDto) {
        return menuService.update(menuDto);
    }

    @Override
    public void delete(String id) {
        menuService.delete(id);
    }
}
