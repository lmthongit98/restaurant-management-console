package com.tma.training.restaurant.domain.repositories;

import com.tma.training.restaurant.domain.models.BillModel;

import java.util.Optional;
import java.util.UUID;

public interface BillRepository {
    Optional<BillModel> findById(UUID id);

    void create(BillModel billModel);

    void update(BillModel billModel);

    void deleteById(UUID id);

    boolean existsById(UUID menuId);

}
