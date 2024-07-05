package com.tma.training.restaurant.infrastructure.persistences.csv.mappers;

import com.tma.training.restaurant.domain.models.BillModel;
import com.tma.training.restaurant.infrastructure.persistences.csv.entities.Bill;
import com.tma.training.restaurant.infrastructure.persistences.mysql.mappers.DataAccessMapper;

import java.util.UUID;

public class CsvBillMapper implements DataAccessMapper<Bill, BillModel> {

    @Override
    public BillModel toModel(Bill bill) {
        return new BillModel.Builder()
                .id(UUID.fromString(bill.getId()))
                .build();
    }

    @Override
    public Bill toEntity(BillModel billModel) {
        return null;
    }

}
