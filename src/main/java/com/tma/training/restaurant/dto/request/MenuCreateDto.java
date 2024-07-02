package com.tma.training.restaurant.dto.request;

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
