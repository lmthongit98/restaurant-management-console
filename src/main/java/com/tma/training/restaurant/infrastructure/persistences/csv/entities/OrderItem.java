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
@CsvFile(name = "order_item.csv")
public class OrderItem extends BaseEntity {

    @Column(name = "bill_id")
    private String billId;

    @Column(name = "menu_id")
    private String menuId;

    @Column(name = "quantities")
    private Integer quantities;

    @Column(name = "unit_price")
    private Double unitPrice;

    public Double getTotalPrice() {
        return unitPrice * quantities;
    }

    @Override
    public String toCsvString() {
        return Stream.of(id, billId, menuId, quantities, unitPrice, createdDate, updatedDate).map(String::valueOf).collect(Collectors.joining(","));
    }

    @Override
    public String toCsvHeader() {
        return AppConstant.ORDER_ITEM_HEADER;
    }
}
