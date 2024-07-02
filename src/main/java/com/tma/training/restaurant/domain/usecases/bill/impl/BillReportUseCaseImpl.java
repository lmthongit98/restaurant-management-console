package com.tma.training.restaurant.domain.usecases.bill.impl;

import com.tma.training.restaurant.common.exceptions.EntityNotFoundException;
import com.tma.training.restaurant.domain.models.BillModel;
import com.tma.training.restaurant.domain.models.MenuModel;
import com.tma.training.restaurant.domain.repositories.BillRepository;
import com.tma.training.restaurant.domain.repositories.MenuRepository;
import com.tma.training.restaurant.domain.usecases.bill.BillReportUseCase;
import com.tma.training.restaurant.dto.response.BillReport;
import com.tma.training.restaurant.dto.response.OrderInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BillReportUseCaseImpl implements BillReportUseCase {

    private final BillRepository billRepository;
    private final MenuRepository menuRepository;


    @Override
    public BillReport getBillReport(UUID billId) {

        BillModel billModel = billRepository.findById(billId).orElseThrow(() -> new EntityNotFoundException("Bill", billId.toString()));

        List<OrderInfo> orderInfoList = billModel.getOrderItems().stream().map(orderItem -> {
            MenuModel menuModel = menuRepository.findById(orderItem.getMenuId()).orElseThrow(() -> new EntityNotFoundException("Menu", orderItem.getMenuId().toString()));
            return new OrderInfo(orderItem.getMenuId(), menuModel.getName(), orderItem.getQuantity(), orderItem.getPrice());
        }).toList();

        return new BillReport(billModel.getId(), orderInfoList, billModel.getTotalAmount());
    }

}
