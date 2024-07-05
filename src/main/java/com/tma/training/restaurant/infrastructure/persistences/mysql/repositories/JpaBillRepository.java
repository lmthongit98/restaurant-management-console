package com.tma.training.restaurant.infrastructure.persistences.mysql.repositories;

import com.tma.training.restaurant.infrastructure.persistences.mysql.entities.BillEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaBillRepository extends JpaRepository<BillEntity, UUID> {
}
