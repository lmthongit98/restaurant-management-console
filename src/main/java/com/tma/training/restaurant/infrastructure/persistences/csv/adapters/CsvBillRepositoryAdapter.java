package com.tma.training.restaurant.infrastructure.persistences.csv.adapters;

import com.tma.training.restaurant.domain.models.BillModel;
import com.tma.training.restaurant.domain.models.OrderItemModel;
import com.tma.training.restaurant.domain.repositories.BillRepository;
import com.tma.training.restaurant.infrastructure.persistences.csv.entities.Bill;
import com.tma.training.restaurant.infrastructure.persistences.csv.entities.OrderItem;
import com.tma.training.restaurant.infrastructure.persistences.csv.repositories.impl.CsvBillRepositoryImpl;
import com.tma.training.restaurant.infrastructure.persistences.csv.repositories.impl.CsvOrderItemRepositoryImpl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

//@Repository
//@Primary
public class CsvBillRepositoryAdapter implements BillRepository {

    CsvBillRepositoryImpl billRepository = CsvBillRepositoryImpl.getInstance();
    CsvOrderItemRepositoryImpl orderItemRepository = CsvOrderItemRepositoryImpl.getInstance();

    @Override
    public Optional<BillModel> findById(UUID id) {
        return billRepository.findById(id.toString()).map(this::toModel);
    }

    private BillModel toModel(Bill bill) {
        List<OrderItem> orderItems = orderItemRepository.findAllByBillId(bill.getId());
        List<OrderItemModel> orderItemModels = orderItems.stream().map(orderItem -> (OrderItemModel) OrderItemModel.builder()
                .id(UUID.fromString(orderItem.getId()))
                .billId(UUID.fromString(orderItem.getBillId()))
//                .menuId(UUID.fromString(orderItem.getMenuId()))
                .price(BigDecimal.valueOf(orderItem.getUnitPrice()))
                .quantity(orderItem.getQuantities())
                .build()).toList();
        return new BillModel.Builder()
                .id(UUID.fromString(bill.getId()))
                .orderItems(orderItemModels)
                .build();
    }

    @Override
    public void create(BillModel billModel) {

    }

    @Override
    public void update(BillModel billModel) {

    }

    @Override
    public void deleteById(UUID id) {

    }

    @Override
    public boolean existsById(UUID menuId) {
        return false;
    }

}
