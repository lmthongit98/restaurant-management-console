package com.tma.training.restaurant.infra.mysql.mapper.impl;

import com.tma.training.restaurant.common.exceptions.EntityNotFoundException;
import com.tma.training.restaurant.domain.models.OrderItemModel;
import com.tma.training.restaurant.infra.mysql.MenuJpaRepository;
import com.tma.training.restaurant.infra.mysql.entities.MenuEntity;
import com.tma.training.restaurant.infra.mysql.entities.OrderItemEntity;
import com.tma.training.restaurant.infra.mysql.mapper.DataAccessMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderItemDataAccessMapper implements DataAccessMapper<OrderItemEntity, OrderItemModel> {

    private MenuJpaRepository menuJpaRepository;

    @Override
    public OrderItemModel toModel(OrderItemEntity orderItemEntity) {
        return OrderItemModel.builder()
                .id(orderItemEntity.getId())
                .billId(orderItemEntity.getBill().getId())
                .menuId(orderItemEntity.getMenuId())
                .quantity(orderItemEntity.getQuantity())
                .price(orderItemEntity.getPrice())
                .build();
    }

    @Override
    public OrderItemEntity toEntity(OrderItemModel orderItemModel) {
        MenuEntity menuEntity = menuJpaRepository.findById(orderItemModel.getMenuId()).orElseThrow(() -> new EntityNotFoundException("MenuId", orderItemModel.getMenuId().toString()));
        return OrderItemEntity.builder()
                .id(orderItemModel.getId())
                .menuId(menuEntity.getId())
                .price(menuEntity.getPrice())
                .quantity(orderItemModel.getQuantity())
                .build();
    }

}
