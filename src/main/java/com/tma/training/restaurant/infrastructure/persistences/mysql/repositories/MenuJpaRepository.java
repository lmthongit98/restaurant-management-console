package com.tma.training.restaurant.infrastructure.persistences.mysql.repositories;

import com.tma.training.restaurant.infrastructure.persistences.mysql.entities.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MenuJpaRepository extends JpaRepository<MenuEntity, UUID> {
}
