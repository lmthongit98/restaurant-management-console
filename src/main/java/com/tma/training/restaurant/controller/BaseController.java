package com.tma.training.restaurant.controller;

import java.util.List;

public interface BaseController<Request, Response> {

    List<Response> findAll();

    Response findById(String id);

    Response create(Request request);

    void delete(String id);
}
