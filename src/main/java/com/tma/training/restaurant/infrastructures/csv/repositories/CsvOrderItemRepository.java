package com.tma.training.restaurant.infrastructures.csv.repositories;

import com.tma.training.restaurant.infrastructures.csv.entities.OrderItem;

import java.util.List;

public interface CsvOrderItemRepository extends CrudRepository<OrderItem, String> {
    List<OrderItem> findAllByBillId(String billId);
}
