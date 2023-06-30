package com.tma.training.restaurant.service;

import java.util.List;

public interface BaseService<Request, Response> {
    List<Response> findAll();

    Response findById(String id);

    Response create(Request request);

    void delete(String id);
}
