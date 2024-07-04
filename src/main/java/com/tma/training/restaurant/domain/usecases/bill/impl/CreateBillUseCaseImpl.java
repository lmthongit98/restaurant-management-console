package com.tma.training.restaurant.domain.usecases.bill.impl;

import com.tma.training.restaurant.commons.exceptions.EntityNotFoundException;
import com.tma.training.restaurant.domain.models.BillModel;
import com.tma.training.restaurant.domain.models.MenuModel;
import com.tma.training.restaurant.domain.models.OrderItemModel;
import com.tma.training.restaurant.domain.repositories.BillRepository;
import com.tma.training.restaurant.domain.repositories.MenuRepository;
import com.tma.training.restaurant.domain.usecases.bill.CreateBillUseCase;
import com.tma.training.restaurant.dtos.request.BillCreateDto;
import com.tma.training.restaurant.dtos.request.OrderItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateBillUseCaseImpl implements CreateBillUseCase {

    private final BillRepository billRepository;
    private final MenuRepository menuRepository;

    @Override
    public void createBill(BillCreateDto billCreateDto) {
        validateOrderItems(billCreateDto);
        BillModel billModel = new BillModel.Builder()
                .id(UUID.randomUUID())
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();

        for (OrderItemDto orderItemDto : billCreateDto.getOrderItems()) {
            MenuModel menuModel = menuRepository.findById(orderItemDto.getMenuId()).orElseThrow(() -> new EntityNotFoundException("Menu", orderItemDto.getMenuId().toString()));
            OrderItemModel orderItem = OrderItemModel.builder()
                    .id(UUID.randomUUID())
                    .billId(billModel.getId())
                    .menu(menuModel)
                    .quantity(orderItemDto.getQuantity())
                    .price(menuModel.getPrice())
                    .createdDate(LocalDateTime.now())
                    .updatedDate(LocalDateTime.now())
                    .build();
            billModel.addOrderItem(orderItem);
        }
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
