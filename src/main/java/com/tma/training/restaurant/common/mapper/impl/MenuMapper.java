package com.tma.training.restaurant.common.mapper.impl;

import com.tma.training.restaurant.common.mapper.Mapper;
import com.tma.training.restaurant.dto.MenuDto;
import com.tma.training.restaurant.infra.csv.entity.Menu;
import org.modelmapper.ModelMapper;

public class MenuMapper implements Mapper<MenuDto, Menu> {

    private final ModelMapper mapper = new ModelMapper();

    private static MenuMapper instance;

    public static MenuMapper getInstance() {
        if (instance == null) {
            instance = new MenuMapper();
        }
        return instance;
    }

    @Override
    public MenuDto mapToDto(Menu menu) {
        return mapper.map(menu, MenuDto.class);
    }

    @Override
    public Menu mapToEntity(MenuDto menuDto) {
        return mapper.map(menuDto, Menu.class);
    }
}
