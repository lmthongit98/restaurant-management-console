package com.tma.training.restaurant.repository;

import com.tma.training.restaurant.entity.OrderItem;

import java.util.List;

public interface OrderItemRepository extends CrudRepository<OrderItem, String> {
    List<OrderItem> findAllByBillId(String billId);
}
