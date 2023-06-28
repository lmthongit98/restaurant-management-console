package com.tma.training.restaurant.entity;

import com.tma.training.restaurant.common.anotations.Column;
import com.tma.training.restaurant.common.anotations.CsvFile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
        return null;
    }

    @Override
    public String toCsvHeader() {
        return null;
    }
}
