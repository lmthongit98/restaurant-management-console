package com.tma.training.restaurant.infrastructures.csv.entities;

import com.tma.training.restaurant.commons.anotations.Column;
import com.tma.training.restaurant.commons.anotations.CsvFile;
import com.tma.training.restaurant.commons.constants.AppConstant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Column(name = "deleted")
    private Boolean deleted = Boolean.FALSE;

    @Override
    public String toCsvString() {
        return Stream.of(id, name, description, image, price, additionalDetails, deleted, createdDate, updatedDate).map(String::valueOf).collect(Collectors.joining(","));
    }

    @Override
    public String toCsvHeader() {
        return AppConstant.MENU_HEADER;
    }
}
