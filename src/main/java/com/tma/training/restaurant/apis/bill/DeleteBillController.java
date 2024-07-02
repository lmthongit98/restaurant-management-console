package com.tma.training.restaurant.apis.bill;

import com.tma.training.restaurant.domain.usecases.bill.DeleteBillUseCase;
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
public class DeleteBillController {

    private final DeleteBillUseCase deleteBillUseCase;

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBill(@PathVariable UUID id) {
        deleteBillUseCase.deleteBill(id);
        return ResponseEntity.ok("Deleted Bill");
    }

}