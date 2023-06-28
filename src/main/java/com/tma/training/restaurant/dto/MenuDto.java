package com.tma.training.restaurant.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuDto {
    private String id;
    private String name;
    private String description;
    private String image;
    private Double price;
    private String additionalDetails;

    @Override
    public String toString() {
        return "ID: " + id + " - Name: " + name  + " - Price: " + price;
    }
}
