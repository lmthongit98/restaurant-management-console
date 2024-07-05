package com.tma.training.restaurant.domain.usecases.bill.impl;

import com.tma.training.restaurant.commons.exceptions.EntityNotFoundException;
import com.tma.training.restaurant.domain.models.BillModel;
import com.tma.training.restaurant.domain.repositories.BillRepository;
import com.tma.training.restaurant.domain.usecases.bill.BillReportUseCase;
import com.tma.training.restaurant.application.dtos.response.BillReport;
import com.tma.training.restaurant.application.dtos.response.OrderInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BillReportUseCaseImpl implements BillReportUseCase {

    private final BillRepository billRepository;

    @Override
    public BillReport getBillReport(UUID billId) {
        BillModel billModel = billRepository.findById(billId).orElseThrow(() -> new EntityNotFoundException("Bill", billId.toString()));
        List<OrderInfo> orderInfoList = billModel.getOrderItems().stream().map(orderItem -> new OrderInfo(orderItem.getMenu().getId(), orderItem.getMenu().getName(), orderItem.getQuantity(), orderItem.getPrice())).toList();
        return new BillReport(billModel.getId(), orderInfoList, billModel.getTotalAmount());
    }

}
