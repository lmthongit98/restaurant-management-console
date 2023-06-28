package com.tma.training.restaurant.entity;

import com.tma.training.restaurant.common.anotations.Column;

public abstract class BaseEntity implements CsvDataModel {

    @Column(name = "id")
    protected String id;

    @Column(name = "created_date")
    protected String createdDate;

    @Column(name = "updated_date")
    protected String updatedDate;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

}
