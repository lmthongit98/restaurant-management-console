package com.tma.training.restaurant.commons.exceptions;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(String entity, String id) {
        super(entity + " with id " + id + " could not be found!");
    }
}
