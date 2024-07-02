package com.tma.training.restaurant.domain.usecases.bill.impl;

import com.tma.training.restaurant.common.exceptions.EntityNotFoundException;
import com.tma.training.restaurant.domain.models.BillModel;
import com.tma.training.restaurant.domain.repositories.BillRepository;
import com.tma.training.restaurant.domain.repositories.MenuRepository;
import com.tma.training.restaurant.domain.usecases.bill.CreateBillUseCase;
import com.tma.training.restaurant.dto.request.BillCreateDto;
import com.tma.training.restaurant.dto.request.OrderItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateBillUseCaseImpl implements CreateBillUseCase {

    private final BillRepository billRepository;
    private final MenuRepository menuRepository;

    @Override
    public void createBill(BillCreateDto billCreateDto) {
        validateOrderItems(billCreateDto);
        BillModel billModel = BillModel.create(billCreateDto);
        billModel.validate();
        billRepository.create(billModel);
    }

    private void validateOrderItems(BillCreateDto billCreateDto) {
        for (OrderItemDto orderItemDto : billCreateDto.getOrderItems()) {
            if (!menuRepository.existsById(orderItemDto.getMenuId())) {
                throw new EntityNotFoundException("Menu ID", orderItemDto.getMenuId().toString());
            }
        }
    }

}
