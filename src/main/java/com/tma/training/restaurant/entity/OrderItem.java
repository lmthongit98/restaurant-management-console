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
@CsvFile(name = "order_item.csv")
public class OrderItem extends BaseEntity {

    @Column(name = "bill_id")
    private Integer billId;

    @Column(name = "menu_id")
    private Integer menuItemID;

    @Column(name = "quantities")
    private Integer quantities;

    @Column(name = "unit_price")
    private Double unitPrice;

    public Double getTotalPrice() {
        return unitPrice * quantities;
    }

    @Override
    public String toCsvString() {
        return null;
    }

    @Override
    public String toCsvHeader() {
        return null;
    }
}
