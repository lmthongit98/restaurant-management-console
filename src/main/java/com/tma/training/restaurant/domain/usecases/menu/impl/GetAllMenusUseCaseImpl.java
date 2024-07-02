package com.tma.training.restaurant.domain.usecases.menu.impl;

import com.tma.training.restaurant.domain.models.MenuModel;
import com.tma.training.restaurant.domain.repositories.MenuRepository;
import com.tma.training.restaurant.domain.usecases.menu.GetAllMenusUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllMenusUseCaseImpl implements GetAllMenusUseCase {

    private final MenuRepository menuRepository;

    @Override
    public List<MenuModel> getAllMenus() {
        return menuRepository.findAll();
    }

}
