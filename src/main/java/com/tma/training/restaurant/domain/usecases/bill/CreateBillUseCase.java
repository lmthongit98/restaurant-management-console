package com.tma.training.restaurant.domain.usecases.bill;

import com.tma.training.restaurant.application.dtos.request.BillCreateDto;

public interface CreateBillUseCase {

    void createBill(BillCreateDto billCreateDto);

}
