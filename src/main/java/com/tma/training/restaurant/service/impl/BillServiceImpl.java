package com.tma.training.restaurant.service.impl;

import com.tma.training.restaurant.common.exceptions.EntityNotFoundException;
import com.tma.training.restaurant.common.mapper.impl.MenuMapper;
import com.tma.training.restaurant.dto.BillDto;
import com.tma.training.restaurant.dto.MenuDto;
import com.tma.training.restaurant.dto.OrderItemDto;
import com.tma.training.restaurant.entity.Bill;
import com.tma.training.restaurant.entity.OrderItem;
import com.tma.training.restaurant.repository.impl.BillRepositoryImpl;
import com.tma.training.restaurant.service.BillService;
import com.tma.training.restaurant.service.OrderItemService;

import java.util.List;

public class BillServiceImpl implements BillService {

    private static BillServiceImpl instance;
    private final MenuMapper mapper;

    private final BillRepositoryImpl repository;
    private final OrderItemService orderItemService;

    static {
        instance = new BillServiceImpl();
    }

    private BillServiceImpl() {
        repository = BillRepositoryImpl.getInstance();
        orderItemService = OrderItemServiceImpl.getInstance();
        mapper = MenuMapper.getInstance();
    }

    public static BillServiceImpl getInstance() {
        if (instance == null) {
            instance = new BillServiceImpl();
        }
        return instance;
    }

    @Override
    public List<BillDto> findAll() {
        return null;
    }

    @Override
    public BillDto findById(String id) {
        return null;
    }

    @Override
    public BillDto create(BillDto dto) {
        return null;
    }

    @Override
    public BillDto update(BillDto dto) {
        return null;
    }

    @Override
    public void delete(String id) {
        Bill bill = findBillById(id);
        repository.delete(bill);
    }

    public Bill findBillById(String id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Bill", id));
    }


    @Override
    public boolean checkMenuInUnpaidBill(String menuId) {
        List<Bill> unpaidBills = repository.findByIsPaidBill(false);
        for (Bill bill : unpaidBills) {
            List<OrderItem> orderItemsOfBill = orderItemService.findAllByBillId(bill.getId());
            if (orderItemsOfBill.stream().anyMatch(orderItemDto -> menuId.equals(orderItemDto.getMenuId()))) {
                return true;
            }
        }
        return false;
    }
}