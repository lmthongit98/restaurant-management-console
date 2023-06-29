package com.tma.training.restaurant.repository;

import com.tma.training.restaurant.entity.Bill;

import java.util.List;

public interface BillRepository extends CrudRepository<Bill, String> {
    List<Bill> findByIsPaidBill(boolean isPaid);
}
