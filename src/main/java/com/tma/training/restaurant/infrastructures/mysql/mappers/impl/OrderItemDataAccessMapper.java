package com.tma.training.restaurant.infrastructures.mysql.mappers.impl;

import com.tma.training.restaurant.domain.models.OrderItemModel;
import com.tma.training.restaurant.infrastructures.mysql.entities.OrderItemEntity;
import com.tma.training.restaurant.infrastructures.mysql.mappers.DataAccessMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderItemDataAccessMapper implements DataAccessMapper<OrderItemEntity, OrderItemModel> {

    private final MenuDataAccessMapper menuDataAccessMapper;

    @Override
    public OrderItemModel toModel(OrderItemEntity orderItemEntity) {
        return OrderItemModel.builder()
                .id(orderItemEntity.getId())
                .billId(orderItemEntity.getBill().getId())
                .menu(menuDataAccessMapper.toModel(orderItemEntity.getMenu()))
                .quantity(orderItemEntity.getQuantity())
                .price(orderItemEntity.getPrice())
                .build();
    }

    @Override
    public OrderItemEntity toEntity(OrderItemModel orderItemModel) {
        return OrderItemEntity.builder()
                .id(orderItemModel.getId())
                .menu(menuDataAccessMapper.toEntity(orderItemModel.getMenu()))
                .price(orderItemModel.getPrice())
                .quantity(orderItemModel.getQuantity())
                .build();
    }

}
