package com.tma.training.restaurant.service.impl;

import com.tma.training.restaurant.common.mapper.impl.MenuMapper;
import com.tma.training.restaurant.dto.OrderItemDto;
import com.tma.training.restaurant.entity.OrderItem;
import com.tma.training.restaurant.repository.OrderItemRepository;
import com.tma.training.restaurant.repository.impl.OrderItemRepositoryImpl;
import com.tma.training.restaurant.service.OrderItemService;

import java.util.List;

public class OrderItemServiceImpl implements OrderItemService {

    private static OrderItemServiceImpl instance;
    private final MenuMapper mapper;

    private final OrderItemRepository repository;

    private OrderItemServiceImpl() {
        repository = OrderItemRepositoryImpl.getInstance();
        mapper = MenuMapper.getInstance();
    }

    public static OrderItemServiceImpl getInstance() {
        if (instance == null) {
            instance = new OrderItemServiceImpl();
        }
        return instance;
    }


    @Override
    public List<OrderItem> findAllByBillId(String billId) {
        return repository.findAllByBillId(billId);
    }
}
