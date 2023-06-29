package com.tma.training.restaurant.service;

import com.tma.training.restaurant.dto.BillDto;

public interface BillService extends BaseService<BillDto> {
    boolean checkMenuInUnpaidBill(String menuId);
}
