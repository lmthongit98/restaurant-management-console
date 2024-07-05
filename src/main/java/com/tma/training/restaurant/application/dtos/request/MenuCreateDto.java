package com.tma.training.restaurant.application.dtos.request;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuCreateDto {
    private String name;
    private String description;
    private String image;
    private BigDecimal price;
    private String additionalDetails;
}
