package com.tma.training.restaurant.domain.usecases.menu.impl;

import com.tma.training.restaurant.domain.models.MenuModel;
import com.tma.training.restaurant.domain.repositories.MenuRepository;
import com.tma.training.restaurant.domain.usecases.menu.MenuCreateUseCase;
import com.tma.training.restaurant.dtos.request.MenuCreateDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MenuCreateUseCaseImpl implements MenuCreateUseCase {

    private final MenuRepository menuRepository;

    @Override
    public void createMenu(MenuCreateDto menuCreateDto) {
        MenuModel menuModel = MenuModel.create(menuCreateDto);
        menuModel.validate();
        menuRepository.create(menuModel);
    }

}
