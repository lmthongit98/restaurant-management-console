package com.tma.training.restaurant.controller.impl;

import com.tma.training.restaurant.controller.BillController;
import com.tma.training.restaurant.dto.request.BillRequestDto;
import com.tma.training.restaurant.dto.request.CustomerOrder;
import com.tma.training.restaurant.dto.response.BillResponseDto;
import com.tma.training.restaurant.service.BillService;
import com.tma.training.restaurant.service.impl.BillServiceImpl;

import java.util.List;

public class BillControllerImpl implements BillController {

    private final BillService service;

    private BillControllerImpl() {
        service = BillServiceImpl.getInstance();
    }

    private static BillControllerImpl instance;

    public static BillControllerImpl getInstance() {
        if (instance == null) {
            instance = new BillControllerImpl();
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
        return service.create(billRequestDto);
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void addMenuItems(String billId, List<CustomerOrder> customerOrders) {
        service.addMenuItems(billId, customerOrders);
    }
}
