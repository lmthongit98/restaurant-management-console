package com.tma.training.restaurant.domain.usecases.menu;

import com.tma.training.restaurant.application.dtos.request.MenuCreateDto;

public interface MenuCreateUseCase {
    void createMenu(MenuCreateDto menuCreateDto);
}
