package com.tma.training.restaurant.entity;

import com.tma.training.restaurant.common.anotations.Column;
import com.tma.training.restaurant.common.anotations.CsvFile;
import lombok.*;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@CsvFile(name = "menu.csv")
public class Menu extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "price")
    private Double price;

    @Column(name = "additional_details")
    private String additionalDetails;

    @Override
    public String toCsvString() {
        return Stream.of(id, name, description, image, price, additionalDetails).map(String::valueOf).collect(Collectors.joining(","));
    }

    @Override
    public String toCsvHeader() {
        return "id,name,description,image,price,additional_details";
    }
}
