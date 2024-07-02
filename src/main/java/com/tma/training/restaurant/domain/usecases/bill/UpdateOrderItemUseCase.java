package com.tma.training.restaurant.domain.usecases.bill;

import com.tma.training.restaurant.dto.request.OrderItemDto;

import java.util.List;
import java.util.UUID;

public interface UpdateOrderItemUseCase {
    void updateOrderItems(UUID billId, List<OrderItemDto> orderItemDtoList);
}
