package com.tma.training.restaurant.infrastructure.persistences.csv.entities;

import com.tma.training.restaurant.commons.anotations.Column;
import com.tma.training.restaurant.commons.anotations.CsvFile;
import com.tma.training.restaurant.commons.constants.AppConstant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@CsvFile(name = "bill.csv")
public class Bill extends BaseEntity {

    @Column(name = "is_paid")
    private Boolean isPaid = Boolean.FALSE;

    @Override
    public String toCsvString() {
        return Stream.of(id, isPaid, createdDate, updatedDate).map(String::valueOf).collect(Collectors.joining(","));
    }

    @Override
    public String toCsvHeader() {
        return AppConstant.BILL_HEADER;
    }

}
