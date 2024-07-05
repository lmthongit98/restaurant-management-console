package com.tma.training.restaurant.infrastructure.persistences.csv.repositories;

import com.tma.training.restaurant.infrastructure.persistences.csv.entities.Bill;

import java.util.List;

public interface CsvBillRepository extends CrudRepository<Bill, String> {
    List<Bill> findByIsPaidBill(boolean isPaid);
}
