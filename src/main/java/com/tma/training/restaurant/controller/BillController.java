package com.tma.training.restaurant.controller;

import com.tma.training.restaurant.dto.request.BillRequestDto;
import com.tma.training.restaurant.dto.request.CustomerOrder;
import com.tma.training.restaurant.dto.response.BillResponseDto;

import java.util.List;

public interface BillController extends BaseController<BillRequestDto, BillResponseDto> {
    void addMenuItems(String billId, List<CustomerOrder> customerOrders);
}
