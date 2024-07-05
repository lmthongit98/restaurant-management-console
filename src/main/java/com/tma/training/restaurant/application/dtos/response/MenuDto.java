package com.tma.training.restaurant.application.dtos.response;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuDto {
    private UUID id;
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
