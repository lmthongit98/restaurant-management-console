package com.tma.training.restaurant.service;

import com.tma.training.restaurant.dto.request.BillRequestDto;
import com.tma.training.restaurant.dto.response.BillResponseDto;
import com.tma.training.restaurant.entity.Bill;

public interface BillService extends BaseService<BillRequestDto, BillResponseDto> {
    boolean checkMenuInUnpaidBill(String menuId);

    Bill findBillById(String billId);
}
