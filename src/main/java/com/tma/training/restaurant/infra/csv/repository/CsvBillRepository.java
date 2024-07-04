package com.tma.training.restaurant.infra.csv.repository;

import com.tma.training.restaurant.infra.csv.entity.Bill;

import java.util.List;

public interface CsvBillRepository extends CrudRepository<Bill, String> {
    List<Bill> findByIsPaidBill(boolean isPaid);
}
