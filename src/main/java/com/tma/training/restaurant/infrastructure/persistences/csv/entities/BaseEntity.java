package com.tma.training.restaurant.infrastructure.persistences.csv.entities;

import com.tma.training.restaurant.commons.anotations.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
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
