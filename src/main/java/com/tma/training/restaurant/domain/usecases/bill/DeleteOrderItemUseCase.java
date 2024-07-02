package com.tma.training.restaurant.domain.usecases.bill;

import java.util.UUID;

public interface DeleteOrderItemUseCase {

    void deleteOrderItem(UUID billId, UUID itemId);

}
