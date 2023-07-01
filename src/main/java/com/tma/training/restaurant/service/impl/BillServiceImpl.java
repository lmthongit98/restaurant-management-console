package com.tma.training.restaurant.service.impl;

import com.tma.training.restaurant.common.exceptions.EntityNotFoundException;
import com.tma.training.restaurant.common.utils.DateUtil;
import com.tma.training.restaurant.dto.MenuDto;
import com.tma.training.restaurant.dto.request.BillRequestDto;
import com.tma.training.restaurant.dto.request.CustomerOrder;
import com.tma.training.restaurant.dto.response.BillResponseDto;
import com.tma.training.restaurant.entity.Bill;
import com.tma.training.restaurant.entity.OrderItem;
import com.tma.training.restaurant.repository.OrderItemRepository;
import com.tma.training.restaurant.repository.impl.BillRepositoryImpl;
import com.tma.training.restaurant.repository.impl.OrderItemRepositoryImpl;
import com.tma.training.restaurant.service.BillService;
import com.tma.training.restaurant.service.MenuService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BillServiceImpl implements BillService {

    private static BillServiceImpl instance;

    private final BillRepositoryImpl billRepository;
    private final OrderItemRepository orderItemRepository;
    private final MenuService menuService;

    private BillServiceImpl() {
        billRepository = BillRepositoryImpl.getInstance();
        orderItemRepository = OrderItemRepositoryImpl.getInstance();
        menuService = MenuServiceImpl.getInstance();
    }

    public static BillServiceImpl getInstance() {
        if (instance == null) {
            instance = new BillServiceImpl();
        }
        return instance;
    }

    @Override
    public List<BillResponseDto> findAll() {
        return null;
    }

    @Override
    public BillResponseDto findById(String id) {
        return null;
    }

    @Override
    public BillResponseDto create(BillRequestDto billRequestDto) {
        Bill bill = new Bill();
        Bill savedBill = billRepository.save(bill);
        addMenuItems(savedBill.getId(), billRequestDto.getCustomerOrders());
        return new BillResponseDto();
    }


    @Override
    public void addMenuItems(String billId, List<CustomerOrder> customerOrders) {
        Bill bill = findBillById(billId);
        if (Boolean.TRUE.equals(bill.getIsPaid())) {
            throw new IllegalStateException("Could not add menu items in a paid bill");
        }
        List<OrderItem> updatedOrderItems = new ArrayList<>();
        List<OrderItem> orderItems = orderItemRepository.findAllByBillId(billId);
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
        orderItemRepository.saveAll(updatedOrderItems);
    }

    @Override
    public void removeMenuItems(String billId, List<CustomerOrder> customerOrders) {
        Bill bill = findBillById(billId);
        if (Boolean.TRUE.equals(bill.getIsPaid())) {
            throw new IllegalStateException("Could not remove menu items in a paid bill");
        }
        List<OrderItem> orderItems = orderItemRepository.findAllByBillId(billId);
        List<OrderItem> orderItemToBeUpdated = new ArrayList<>();
        List<OrderItem> orderItemToBeRemoved = new ArrayList<>();
        Map<String, OrderItem> menuIdOrderItemMap = orderItems.stream().collect(Collectors.toMap(OrderItem::getMenuId, Function.identity()));
        for (CustomerOrder customerOrder : customerOrders) {
            OrderItem orderItem = menuIdOrderItemMap.get(customerOrder.getMenuId());
            if (orderItem == null) {
                throw new EntityNotFoundException("Order item", customerOrder.getMenuId());
            }
            int updatedQuantity = orderItem.getQuantities() - customerOrder.getQuantity();
            if (updatedQuantity > 0) {
                orderItem.setQuantities(updatedQuantity);
                orderItemToBeUpdated.add(orderItem);
            } else if (updatedQuantity == 0) {
                orderItemToBeRemoved.add(orderItem);
            } else {
                throw new IllegalStateException("Quantity could must not less than 0");
            }
        }
        orderItemRepository.saveAll(orderItemToBeUpdated);
        orderItemRepository.deleteAll(orderItemToBeRemoved);
    }

    @Override
    public void delete(String id) {
        Bill bill = findBillById(id);
        billRepository.delete(bill);
    }

    @Override
    public Bill findBillById(String id) {
        return billRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Bill", id));
    }


    @Override
    public boolean checkMenuInUnpaidBill(String menuId) {
        List<Bill> unpaidBills = billRepository.findByIsPaidBill(false);
        for (Bill bill : unpaidBills) {
            List<OrderItem> orderItemsOfBill = orderItemRepository.findAllByBillId(bill.getId());
            if (orderItemsOfBill.stream().anyMatch(orderItemDto -> menuId.equals(orderItemDto.getMenuId()))) {
                return true;
            }
        }
        return false;
    }
}
