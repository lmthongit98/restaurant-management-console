package com.tma.training.restaurant.domain.usecases.bill.impl;

import com.tma.training.restaurant.commons.exceptions.EntityNotFoundException;
import com.tma.training.restaurant.domain.models.BillModel;
import com.tma.training.restaurant.domain.models.MenuModel;
import com.tma.training.restaurant.domain.models.OrderItemModel;
import com.tma.training.restaurant.domain.repositories.BillRepository;
import com.tma.training.restaurant.domain.repositories.MenuRepository;
import com.tma.training.restaurant.domain.usecases.bill.UpdateOrderItemUseCase;
import com.tma.training.restaurant.application.dtos.request.OrderItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UpdateOrderItemUseCaseImpl implements UpdateOrderItemUseCase {

    private final BillRepository billRepository;
    private final MenuRepository menuRepository;

    @Override
    public void updateOrderItems(UUID billId, List<OrderItemDto> updatedOrderItems) {
        BillModel billModel = billRepository.findById(billId).orElseThrow(() -> new EntityNotFoundException("Bill", billId.toString()));

        //  Remove items that are not in the updatedOrderItems
        billModel.getOrderItems().removeIf(orderItem -> updatedOrderItems.stream().noneMatch(updatedItem -> updatedItem.getMenuId().equals(orderItem.getMenu().getId())));

        Map<UUID, OrderItemModel> menuIdOrderItemMap = billModel.getOrderItems().stream().collect(Collectors.toMap(orderItemModel -> orderItemModel.getMenu().getId(), Function.identity()));
        for (OrderItemDto orderItemDto : updatedOrderItems) {
            OrderItemModel orderItem = menuIdOrderItemMap.get(orderItemDto.getMenuId());
            if (orderItem == null) { // new item
                MenuModel menuModel = menuRepository.findById(orderItemDto.getMenuId()).orElseThrow(() -> new EntityNotFoundException("Menu", orderItemDto.getMenuId().toString()));
                orderItem = OrderItemModel.builder()
                        .id(UUID.randomUUID())
                        .billId(billId)
                        .menu(menuModel)
                        .quantity(orderItemDto.getQuantity())
                        .price(menuModel.getPrice())
                        .createdDate(LocalDateTime.now())
                        .updatedDate(LocalDateTime.now())
                        .build();
                billModel.addOrderItem(orderItem);
            } else { // existing item
                orderItem.setQuantity(orderItemDto.getQuantity());
            }
        }
        billRepository.update(billModel);
    }

}
