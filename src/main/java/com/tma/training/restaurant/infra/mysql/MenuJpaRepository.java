package com.tma.training.restaurant.infra.mysql;

import com.tma.training.restaurant.infra.mysql.entities.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MenuJpaRepository extends JpaRepository<MenuEntity, UUID> {
}
