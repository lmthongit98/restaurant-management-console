package com.tma.training.restaurant.infrastructure.persistences.csv.adapters;

import com.tma.training.restaurant.domain.models.BillModel;
import com.tma.training.restaurant.domain.models.MenuModel;
import com.tma.training.restaurant.domain.models.OrderItemModel;
import com.tma.training.restaurant.domain.repositories.BillRepository;
import com.tma.training.restaurant.infrastructure.persistences.csv.entities.Bill;
import com.tma.training.restaurant.infrastructure.persistences.csv.entities.OrderItem;
import com.tma.training.restaurant.infrastructure.persistences.csv.mappers.CsvBillMapper;
import com.tma.training.restaurant.infrastructure.persistences.csv.mappers.CsvMenuMapper;
import com.tma.training.restaurant.infrastructure.persistences.csv.mappers.CsvOrderItemMapper;
import com.tma.training.restaurant.infrastructure.persistences.csv.repositories.CsvMenuRepository;
import com.tma.training.restaurant.infrastructure.persistences.csv.repositories.impl.CsvBillRepositoryImpl;
import com.tma.training.restaurant.infrastructure.persistences.csv.repositories.impl.CsvMenuRepositoryImpl;
import com.tma.training.restaurant.infrastructure.persistences.csv.repositories.impl.CsvOrderItemRepositoryImpl;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

//@Repository
//@Primary
public class CsvBillRepositoryAdapter implements BillRepository {

    private final CsvBillRepositoryImpl billRepository = CsvBillRepositoryImpl.getInstance();
    private final CsvOrderItemRepositoryImpl orderItemRepository = CsvOrderItemRepositoryImpl.getInstance();
    private final CsvMenuRepository csvMenuRepository = CsvMenuRepositoryImpl.getInstance();

    private final CsvOrderItemMapper csvOrderItemMapper = new CsvOrderItemMapper();
    private final CsvBillMapper csvBillMapper = new CsvBillMapper();
    private final CsvMenuMapper csvMenuMapper = new CsvMenuMapper();

    @Override
    public Optional<BillModel> findById(UUID id) { // todo: not tested yet
        BillModel billModel = billRepository.findById(id.toString()).map(csvBillMapper::toModel).orElse(null);
        if (Objects.isNull(billModel)) {
            return Optional.empty();
        }

        List<OrderItem> orderItems = orderItemRepository.findAllByBillId(id.toString());
        List<OrderItemModel> orderItemModels = orderItems.stream().map(csvOrderItemMapper::toModel).toList();
        for (OrderItemModel orderItemModel : orderItemModels) {
            MenuModel menuModel = csvMenuRepository.findById(orderItemModel.getMenu().getId().toString()).map(csvMenuMapper::toModel).orElseThrow();
            orderItemModel.setMenu(menuModel);
            billModel.addOrderItem(orderItemModel);
        }
        return Optional.of(billModel);
    }

    @Override
    public void create(BillModel billModel) {

    }

    @Override
    public void update(BillModel billModel) { // todo: not tested yet

        Bill bill = billRepository.findById(billModel.getId().toString()).orElseThrow();
        bill.setUpdatedDate(billModel.getUpdatedDate().toString());

        // delete
        List<OrderItem> existingOrderItems = orderItemRepository.findAllByBillId(bill.getId());
        Map<String, OrderItemModel> menuIdOrderItemMap = billModel.getOrderItems().stream().collect(Collectors.toMap(o -> o.getMenu().getId().toString(), Function.identity()));
        for (OrderItem orderItem : existingOrderItems) {
            OrderItemModel existingOrderItemModel = menuIdOrderItemMap.get(orderItem.getMenuId());
            if (existingOrderItemModel == null) { // delete
                orderItemRepository.deleteById(orderItem.getId());
            } else { // update only quantity
                orderItem.setQuantities(existingOrderItemModel.getQuantity());
                orderItemRepository.save(orderItem);
            }
        }

        // add new
        List<OrderItemModel> newOrderItemModels = billModel.getOrderItems().stream().filter(o -> existingOrderItems.stream().noneMatch(eo -> eo.getMenuId().equals(o.getMenu().getId().toString()))).toList();
        for (OrderItemModel orderItemModel : newOrderItemModels) {
            OrderItem orderItem = csvOrderItemMapper.toEntity(orderItemModel);
            orderItemRepository.save(orderItem);
        }

    }

    @Override
    public void deleteById(UUID id) {

    }

    @Override
    public boolean existsById(UUID menuId) {
        return false;
    }

}
