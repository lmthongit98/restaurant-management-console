package com.tma.training.restaurant.infrastructures.mysql.mappers.impl;

import com.tma.training.restaurant.domain.models.BillModel;
import com.tma.training.restaurant.infrastructures.mysql.entities.BillEntity;
import com.tma.training.restaurant.infrastructures.mysql.entities.OrderItemEntity;
import com.tma.training.restaurant.infrastructures.mysql.mappers.DataAccessMapper;
import org.springframework.stereotype.Component;

@Component
public class BillDataAccessMapper implements DataAccessMapper<BillEntity, BillModel> {

    private final OrderItemDataAccessMapper orderItemMapper;

    public BillDataAccessMapper(OrderItemDataAccessMapper orderItemMapper) {
        this.orderItemMapper = orderItemMapper;
    }

    @Override
    public BillModel toModel(BillEntity billEntity) {
        return new BillModel.Builder()
                .id(billEntity.getId())
                .createdDate(billEntity.getCreatedDate())
                .updatedDate(billEntity.getUpdatedDate())
                .orderItems(billEntity.getOrderItems().stream().map(orderItemMapper::toModel).toList())
                .build();
    }

    @Override
    public BillEntity toEntity(BillModel billModel) {
        BillEntity billEntity = BillEntity.builder()
                .id(billModel.getId())
                .createdDate(billModel.getCreatedDate())
                .updatedDate(billModel.getUpdatedDate())
                .build();

        billModel.getOrderItems().forEach(orderItemModel -> {
            OrderItemEntity orderItemEntity = orderItemMapper.toEntity(orderItemModel);
            orderItemEntity.setBill(billEntity);
            billEntity.getOrderItems().add(orderItemEntity);
        });

        return billEntity;
    }

}
