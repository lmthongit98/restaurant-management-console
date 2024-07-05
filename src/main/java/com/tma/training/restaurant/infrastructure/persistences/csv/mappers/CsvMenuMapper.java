package com.tma.training.restaurant.infrastructure.persistences.csv.mappers;

import com.tma.training.restaurant.domain.models.MenuModel;
import com.tma.training.restaurant.domain.models.OrderItemModel;
import com.tma.training.restaurant.infrastructure.persistences.csv.entities.Menu;
import com.tma.training.restaurant.infrastructure.persistences.csv.entities.OrderItem;
import com.tma.training.restaurant.infrastructure.persistences.mysql.mappers.DataAccessMapper;

public class CsvMenuMapper implements DataAccessMapper<Menu, MenuModel> {


    @Override
    public MenuModel toModel(Menu menu) {
        return null;
    }

    @Override
    public Menu toEntity(MenuModel menuModel) {
        return null;
    }
}
