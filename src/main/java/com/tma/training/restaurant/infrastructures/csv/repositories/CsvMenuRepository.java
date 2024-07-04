package com.tma.training.restaurant.infrastructures.csv.repositories;

import com.tma.training.restaurant.infrastructures.csv.entities.Menu;

import java.util.List;
import java.util.Optional;

public interface CsvMenuRepository extends CrudRepository<Menu, String>{
    List<Menu> findByDeleted(boolean deleted);

    Optional<Menu> findByIdAndDeleted(String id, boolean deleted);
}
