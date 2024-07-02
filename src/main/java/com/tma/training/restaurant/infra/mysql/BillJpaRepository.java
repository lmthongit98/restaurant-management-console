package com.tma.training.restaurant.infra.mysql;

import com.tma.training.restaurant.infra.mysql.entities.BillEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BillJpaRepository extends JpaRepository<BillEntity, UUID> {
}
