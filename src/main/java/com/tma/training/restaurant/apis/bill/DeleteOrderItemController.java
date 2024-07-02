package com.tma.training.restaurant.apis.bill;

import com.tma.training.restaurant.domain.usecases.bill.DeleteOrderItemUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/bills")
@RequiredArgsConstructor
public class DeleteOrderItemController {

    private final DeleteOrderItemUseCase deleteOrderItemUseCase;

    @DeleteMapping("/{billId}/items/{itemId}")
    public ResponseEntity<?> deleteOrderItem(@PathVariable UUID billId, @PathVariable UUID itemId) {
        deleteOrderItemUseCase.deleteOrderItem(billId, itemId);
        return ResponseEntity.ok("Deleted Order Items");
    }

}
