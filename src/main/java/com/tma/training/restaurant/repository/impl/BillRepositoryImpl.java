package com.tma.training.restaurant.repository.impl;

import com.tma.training.restaurant.entity.Bill;
import com.tma.training.restaurant.repository.BillRepository;

import java.util.List;

public class BillRepositoryImpl extends SimpleCsvRepository<Bill> implements BillRepository {
    private static BillRepositoryImpl instance;

    private BillRepositoryImpl() {
    }

    public static BillRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new BillRepositoryImpl();
        }
        return instance;
    }

    @Override
    public List<Bill> findByIsPaidBill(boolean isPaid) {
        return findAll().stream().filter(bill -> bill.getIsPaid() == isPaid).toList();
    }
}
