package com.tma.training.restaurant.apis.bill;

import com.tma.training.restaurant.domain.usecases.bill.UpdateOrderItemUseCase;
import com.tma.training.restaurant.dto.request.OrderItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/bills")
@RequiredArgsConstructor
public class UpdateBillController {

    private final UpdateOrderItemUseCase updateOrderItemUseCase;

    @PutMapping("/{id}")
    public ResponseEntity<?> createBill(@PathVariable UUID id, @RequestBody List<OrderItemDto> orderItemDtoList) {
        updateOrderItemUseCase.updateOrderItems(id, orderItemDtoList);
        return ResponseEntity.ok("Updated Bill");
    }

}
