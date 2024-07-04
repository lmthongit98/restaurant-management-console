package com.tma.training.restaurant.infrastructures.csv.repositories.impl;

import com.tma.training.restaurant.infrastructures.csv.entities.Bill;
import com.tma.training.restaurant.infrastructures.csv.repositories.CsvBillRepository;

import java.util.List;

public class CsvBillRepositoryImpl extends SimpleCsvRepository<Bill> implements CsvBillRepository {
    private static CsvBillRepositoryImpl instance;

    private CsvBillRepositoryImpl() {
    }

    public static CsvBillRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new CsvBillRepositoryImpl();
        }
        return instance;
    }

    @Override
    public List<Bill> findByIsPaidBill(boolean isPaid) {
        return findAll().stream().filter(bill -> bill.getIsPaid() == isPaid).toList();
    }

    public boolean isBillExist(String billId) {
        return data.containsKey(billId);
    }
}
