package com.tma.training.restaurant.service;

import com.tma.training.restaurant.dto.MenuDto;

public interface MenuService extends BaseService<MenuDto, MenuDto> {
    MenuDto update(MenuDto menuDto);
}
