package com.tma.training.restaurant.domain.models;

import com.tma.training.restaurant.application.dtos.request.MenuCreateDto;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class MenuModel extends BaseModel {

    public MenuModel(UUID id) {
        this.id = id;
    }

    private String name;

    private String description;

    private String image;

    private BigDecimal price;

    private String additionalDetails;

    public static MenuModel create(MenuCreateDto menuCreateDto) {
        MenuModel menuModel = MenuModel.builder()
                .id(UUID.randomUUID())
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .name(menuCreateDto.getName())
                .description(menuCreateDto.getDescription())
                .image(menuCreateDto.getImage())
                .price(menuCreateDto.getPrice())
                .additionalDetails(menuCreateDto.getAdditionalDetails())
                .build();
        menuModel.validate();
        return menuModel;
    }


    public void validate() {
        // entity business rules validation to protect it's invariant before saving entity to a database
    }


}
