package com.tma.training.restaurant.infrastructure.persistences.mysql.repositories;

import com.tma.training.restaurant.infrastructure.persistences.mysql.entities.BillEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BillJpaRepository extends JpaRepository<BillEntity, UUID> {
}
