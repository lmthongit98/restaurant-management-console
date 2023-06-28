package com.tma.training.restaurant.entity;

public interface CsvDataModel {
    String getId();
    String toCsvString();
    String toCsvHeader();
}
