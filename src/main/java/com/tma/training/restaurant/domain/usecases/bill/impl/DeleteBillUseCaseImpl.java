package com.tma.training.restaurant.domain.usecases.bill.impl;

import com.tma.training.restaurant.commons.exceptions.EntityNotFoundException;
import com.tma.training.restaurant.domain.repositories.BillRepository;
import com.tma.training.restaurant.domain.usecases.bill.DeleteBillUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteBillUseCaseImpl implements DeleteBillUseCase {

    private final BillRepository billRepository;

    @Override
    public void deleteBill(UUID billId) {
        if (!billRepository.existsById(billId)) {
            throw new EntityNotFoundException("Bill ID", billId.toString());
        }
        billRepository.deleteById(billId);
    }

}
