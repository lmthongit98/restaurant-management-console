package com.tma.training.restaurant.domain.usecases.bill.impl;

import com.tma.training.restaurant.common.exceptions.EntityNotFoundException;
import com.tma.training.restaurant.domain.models.BillModel;
import com.tma.training.restaurant.domain.repositories.BillRepository;
import com.tma.training.restaurant.domain.usecases.bill.UpdateOrderItemUseCase;
import com.tma.training.restaurant.dto.request.OrderItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdateOrderItemUseCaseImpl implements UpdateOrderItemUseCase {

    private final BillRepository billRepository;

    @Override
    public void updateOrderItems(UUID billId, List<OrderItemDto> orderItemDtoList) {
        BillModel billModel = billRepository.findById(billId).orElseThrow(() -> new EntityNotFoundException("Bill", billId.toString()));
        billModel.updateOrderItems(orderItemDtoList);
        billRepository.update(billModel);
    }

}
