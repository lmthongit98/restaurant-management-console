package com.tma.training.restaurant.infrastructure.persistences.mysql.mappers.impl;

import com.tma.training.restaurant.domain.models.MenuModel;
import com.tma.training.restaurant.infrastructure.persistences.mysql.entities.MenuEntity;
import com.tma.training.restaurant.infrastructure.persistences.mysql.mappers.DataAccessMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MenuDataAccessMapper implements DataAccessMapper<MenuEntity, MenuModel> {

    @Override
    public MenuModel toModel(MenuEntity menuEntity) {
        return MenuModel.builder()
                .id(menuEntity.getId())
                .name(menuEntity.getName())
                .description(menuEntity.getDescription())
                .price(menuEntity.getPrice())
                .image(menuEntity.getImage())
                .createdDate(menuEntity.getCreatedDate())
                .updatedDate(menuEntity.getUpdatedDate())
                .build();
    }

    @Override
    public MenuEntity toEntity(MenuModel menuModel) {
        return MenuEntity.builder()
                .id(menuModel.getId())
                .name(menuModel.getName())
                .description(menuModel.getDescription())
                .price(menuModel.getPrice())
                .image(menuModel.getImage())
                .createdDate(menuModel.getCreatedDate())
                .updatedDate(menuModel.getUpdatedDate())
                .build();
    }

}
