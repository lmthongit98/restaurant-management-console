package com.tma.training.restaurant.infrastructures.mysql.repositories;

import com.tma.training.restaurant.infrastructures.mysql.entities.BillEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BillJpaRepository extends JpaRepository<BillEntity, UUID> {
}
