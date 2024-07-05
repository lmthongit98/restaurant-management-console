package com.tma.training.restaurant.domain.usecases.bill;

import java.util.UUID;

public interface DeleteBillUseCase {

    void deleteBill(UUID billId);

}
