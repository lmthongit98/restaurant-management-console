package com.tma.training.restaurant.domain.usecases.bill.impl;

import com.tma.training.restaurant.commons.exceptions.EntityNotFoundException;
import com.tma.training.restaurant.domain.models.BillModel;
import com.tma.training.restaurant.domain.repositories.BillRepository;
import com.tma.training.restaurant.domain.usecases.bill.DeleteOrderItemUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteOrderItemUseCaseImpl implements DeleteOrderItemUseCase {

    private final BillRepository billRepository;

    @Override
    public void deleteOrderItem(UUID billId, UUID menuId) {
        BillModel billModel = billRepository.findById(billId).orElseThrow(() -> new EntityNotFoundException("Bill", billId.toString()));
        billModel.removeOrderItem(menuId);
        billRepository.update(billModel);
    }

}
