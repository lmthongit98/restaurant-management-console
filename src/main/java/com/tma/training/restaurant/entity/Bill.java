package com.tma.training.restaurant.entity;

import com.tma.training.restaurant.common.anotations.Column;
import com.tma.training.restaurant.common.anotations.CsvFile;
import com.tma.training.restaurant.common.constants.AppConstant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@CsvFile(name = "bill.csv")
public class Bill extends BaseEntity {

    @Column(name = "is_paid")
    private Boolean isPaid;

    @Override
    public String toCsvString() {
        return Stream.of(id, isPaid, createdDate, updatedDate).map(String::valueOf).collect(Collectors.joining(","));
    }

    @Override
    public String toCsvHeader() {
        return AppConstant.BILL_HEADER;
    }

}
