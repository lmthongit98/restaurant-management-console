package com.tma.training.restaurant.common.mapper;

public interface Mapper<Dto, Entity> {
    Dto mapToDto(Entity entity);
    Entity mapToEntity(Dto dto);
}
