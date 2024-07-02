package com.tma.training.restaurant.domain.usecases.menu;

import com.tma.training.restaurant.dto.request.MenuCreateDto;

public interface MenuCreateUseCase {
    void createMenu(MenuCreateDto menuCreateDto);
}
