package com.tma.training.restaurant.domain.usecases.bill;

import com.tma.training.restaurant.application.dtos.request.OrderItemDto;

import java.util.List;
import java.util.UUID;

public interface UpdateOrderItemUseCase {
    void updateOrderItems(UUID billId, List<OrderItemDto> orderItemDtoList);
}
