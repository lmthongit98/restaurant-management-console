package com.tma.training.restaurant.entity;

import com.tma.training.restaurant.common.anotations.Column;

public abstract class BaseEntity implements CsvDataModel {
    @Column(name = "id")
    private String id;

    @Column(name = "created_date")
    private String createdDate;

    @Column(name = "updated_date")
    private String updatedDate;

    @Override
    public String getId() {
        return id;
    }
}
