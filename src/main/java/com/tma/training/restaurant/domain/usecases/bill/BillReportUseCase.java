package com.tma.training.restaurant.domain.usecases.bill;

import com.tma.training.restaurant.dtos.response.BillReport;

import java.util.UUID;

public interface BillReportUseCase {

    BillReport getBillReport(UUID billId);

}
