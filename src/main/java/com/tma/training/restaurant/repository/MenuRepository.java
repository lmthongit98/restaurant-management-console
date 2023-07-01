package com.tma.training.restaurant.repository;

import com.tma.training.restaurant.entity.Menu;

import java.util.List;
import java.util.Optional;

public interface MenuRepository extends CrudRepository<Menu, String>{
    List<Menu> findByDeleted(boolean deleted);

    Optional<Menu> findByIdAndDeleted(String id, boolean deleted);
}
