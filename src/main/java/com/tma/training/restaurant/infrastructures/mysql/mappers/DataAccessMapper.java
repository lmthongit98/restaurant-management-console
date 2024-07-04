package com.tma.training.restaurant.infrastructures.mysql.mappers;

public interface DataAccessMapper<Entity, Model> {
    Model toModel(Entity entity);
    Entity toEntity(Model model);
}
