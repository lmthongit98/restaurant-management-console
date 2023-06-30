package com.tma.training.restaurant.controller;

import com.tma.training.restaurant.dto.MenuDto;

public interface MenuController extends BaseController<MenuDto, MenuDto> {
    MenuDto update(MenuDto menuDto);
}
