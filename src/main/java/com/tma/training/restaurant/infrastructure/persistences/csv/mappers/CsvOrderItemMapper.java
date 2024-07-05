package com.tma.training.restaurant.infrastructure.persistences.csv.mappers;

import com.tma.training.restaurant.domain.models.MenuModel;
import com.tma.training.restaurant.domain.models.OrderItemModel;
import com.tma.training.restaurant.infrastructure.persistences.csv.entities.OrderItem;
import com.tma.training.restaurant.infrastructure.persistences.mysql.mappers.DataAccessMapper;

import java.math.BigDecimal;
import java.util.UUID;

public class CsvOrderItemMapper implements DataAccessMapper<OrderItem, OrderItemModel> {

    @Override
    public OrderItemModel toModel(OrderItem orderItem) {
        return OrderItemModel.builder()
                .id(UUID.fromString(orderItem.getId()))
                .billId(UUID.fromString(orderItem.getBillId()))
                .menu(new MenuModel(UUID.fromString(orderItem.getMenuId())))
                .price(BigDecimal.valueOf(orderItem.getUnitPrice()))
                .quantity(orderItem.getQuantities()).build();
    }

    @Override
    public OrderItem toEntity(OrderItemModel orderItemModel) {
        return null;
    }

}
