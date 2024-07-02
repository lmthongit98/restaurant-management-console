package com.tma.training.restaurant.infra.mysql.mapper;

public interface DataAccessMapper<Entity, Model> {
    Model toModel(Entity entity);
    Entity toEntity(Model model);
}
