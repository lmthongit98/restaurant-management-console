package com.tma.training.restaurant.entity;

public interface CsvDataModel {
    String getId();
    void setId(String id);
    String toCsvString();
    String toCsvHeader();
    void setCreatedDate(String createdDate);
    void setUpdatedDate(String updatedDate);
}
