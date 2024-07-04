package com.tma.training.restaurant.apis.bill;

import com.tma.training.restaurant.domain.usecases.bill.CreateBillUseCase;
import com.tma.training.restaurant.dtos.request.BillCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bills")
@RequiredArgsConstructor
public class CreateBillController {

    private final CreateBillUseCase createBillUseCase;

    @PostMapping
    public ResponseEntity<?> createBill(@RequestBody BillCreateDto billCreateDto) {
        createBillUseCase.createBill(billCreateDto);
        return ResponseEntity.ok("Created Bill");
    }

}
