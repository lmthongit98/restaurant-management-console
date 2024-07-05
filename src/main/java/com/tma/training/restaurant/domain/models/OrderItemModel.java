package com.tma.training.restaurant.domain.models;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, of = {"billId", "menu"})
public class OrderItemModel extends BaseModel {

    private UUID billId;

    private MenuModel menu;

    private int quantity;

    private BigDecimal price;

    public void validate() {
        if (Objects.isNull(menu)) {
            throw new IllegalStateException("Menu can not be null");
        }
        if (Objects.isNull(billId)) {
            throw new IllegalStateException("Bill Id can not be null");
        }
        if (quantity <= 0) {
            throw new IllegalStateException("Quantity must greater than 0");
        }
        if (Objects.isNull(price)) {
            throw new IllegalStateException("Price can not be null");
        }
    }
}
