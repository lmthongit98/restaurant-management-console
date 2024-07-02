package com.tma.training.restaurant.apis.bill;

import com.tma.training.restaurant.domain.usecases.bill.BillReportUseCase;
import com.tma.training.restaurant.domain.usecases.bill.CreateBillUseCase;
import com.tma.training.restaurant.dto.request.BillCreateDto;
import com.tma.training.restaurant.dto.response.BillReport;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/bills")
@RequiredArgsConstructor
public class BillReportController {

    private final BillReportUseCase billReportUseCase;

    @GetMapping("/{id}")
    public ResponseEntity<BillReport> getBillById(@PathVariable UUID id) {
        BillReport billReport = billReportUseCase.getBillReport(id);
        return ResponseEntity.ok(billReport);
    }

}
