package com.tma.training.restaurant.common.exceptions;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(String entity, String id) {
        super(entity + " with id " + id + " could not be found!");
    }
}
