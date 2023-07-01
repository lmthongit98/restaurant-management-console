package com.tma.training.restaurant.service;

import com.tma.training.restaurant.dto.request.BillRequestDto;
import com.tma.training.restaurant.dto.request.CustomerOrder;
import com.tma.training.restaurant.dto.response.BillResponseDto;
import com.tma.training.restaurant.entity.Bill;

import java.util.List;

public interface BillService extends BaseService<BillRequestDto, BillResponseDto> {
    boolean checkMenuInUnpaidBill(String menuId);

    Bill findBillById(String billId);
    void addMenuItems(String billId, List<CustomerOrder> customerOrders);

    void removeMenuItems(String billId, List<CustomerOrder> customerOrders);
}
