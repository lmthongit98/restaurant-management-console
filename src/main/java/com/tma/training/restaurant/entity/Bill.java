package com.tma.training.restaurant.entity;

import com.tma.training.restaurant.common.anotations.Column;
import com.tma.training.restaurant.common.anotations.CsvFile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@CsvFile(name = "bill.csv")
public class Bill extends BaseEntity{

    @Column(name = "is_paid")
    private Boolean isPaid;

    @Override
    public String toCsvString() {
        return null;
    }

    @Override
    public String toCsvHeader() {
        return null;
    }
}
