package com.tma.training.restaurant.service.impl;

import com.tma.training.restaurant.common.mapper.impl.MenuMapper;
import com.tma.training.restaurant.dto.MenuDto;
import com.tma.training.restaurant.dto.OrderItemDto;
import com.tma.training.restaurant.dto.request.CustomerOrder;
import com.tma.training.restaurant.entity.Bill;
import com.tma.training.restaurant.entity.Menu;
import com.tma.training.restaurant.entity.OrderItem;
import com.tma.training.restaurant.repository.OrderItemRepository;
import com.tma.training.restaurant.repository.impl.OrderItemRepositoryImpl;
import com.tma.training.restaurant.service.BillService;
import com.tma.training.restaurant.service.MenuService;
import com.tma.training.restaurant.service.OrderItemService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OrderItemServiceImpl implements OrderItemService {

    private static OrderItemServiceImpl instance;
    private final MenuMapper mapper;

    private final OrderItemRepository repository;
    private final BillService billService;
    private final MenuService menuService;

    static {
        instance = new OrderItemServiceImpl();
    }

    private OrderItemServiceImpl() {
        repository = OrderItemRepositoryImpl.getInstance();
        billService = BillServiceImpl.getInstance();
        menuService = MenuServiceImpl.getInstance();
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

    @Override
    public void addMenuItems(String billId, List<CustomerOrder> customerOrders) {
        Bill bill = billService.findBillById(billId); // will throw exception if bill not found
        List<OrderItem> updatedOrderItems = new ArrayList<>();
        List<OrderItem> orderItems = findAllByBillId(billId);
        Map<String, OrderItem> menuIdOrderItemMap = orderItems.stream().collect(Collectors.toMap(OrderItem::getMenuId, Function.identity()));
        for (CustomerOrder customerOrder : customerOrders) {
            String menuId = customerOrder.getMenuId();
            OrderItem orderItem = menuIdOrderItemMap.get(menuId);
            if (orderItem == null) {
                MenuDto menuDto = menuService.findById(menuId); // will throw exception if menu is not found
                orderItem = OrderItem.builder()
                        .billId(billId)
                        .menuId(menuId)
                        .quantities(customerOrder.getQuantity())
                        .unitPrice(menuDto.getPrice())
                        .build();
            } else {
                orderItem.setQuantities(orderItem.getQuantities() + customerOrder.getQuantity());
            }
            updatedOrderItems.add(orderItem);
        }
        repository.saveAll(updatedOrderItems);
    }
}
