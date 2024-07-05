package com.tma.training.restaurant.infrastructures.mysql.adapters;

import com.tma.training.restaurant.commons.exceptions.EntityNotFoundException;
import com.tma.training.restaurant.domain.models.BillModel;
import com.tma.training.restaurant.domain.repositories.BillRepository;
import com.tma.training.restaurant.infrastructures.mysql.entities.BillEntity;
import com.tma.training.restaurant.infrastructures.mysql.entities.OrderItemEntity;
import com.tma.training.restaurant.infrastructures.mysql.mappers.impl.BillDataAccessMapper;
import com.tma.training.restaurant.infrastructures.mysql.mappers.impl.OrderItemDataAccessMapper;
import com.tma.training.restaurant.infrastructures.mysql.repositories.BillJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class BillRepositoryImpl implements BillRepository {

    private final BillJpaRepository billJpaRepository;
    private final BillDataAccessMapper billDataAccessMapper;
    private final OrderItemDataAccessMapper orderItemDataAccessMapper;

    @Override
    public Optional<BillModel> findById(UUID id) {
        return billJpaRepository.findById(id).map(billDataAccessMapper::toModel);
    }

    @Override
    public void create(BillModel billModel) {
        billJpaRepository.save(billDataAccessMapper.toEntity(billModel));
    }

    @Override
    public void update(BillModel billModel) {
        BillEntity existingEntity = billJpaRepository.findById(billModel.getId()).orElseThrow(() -> new EntityNotFoundException("Bill", billModel.getId().toString()));
        updateBillEntity(existingEntity, billModel);
        billJpaRepository.save(existingEntity);
    }

    @Override
    public void deleteById(UUID id) {
        billJpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(UUID id) {
        return billJpaRepository.existsById(id);
    }

    private void updateBillEntity(BillEntity billEntity, BillModel billModel) {
        billEntity.setUpdatedDate(billModel.getUpdatedDate());
        billEntity.getOrderItems().clear();
        List<OrderItemEntity> orderItemEntities = billModel.getOrderItems().stream().map(orderItemDataAccessMapper::toEntity).toList();
        orderItemEntities.forEach(billEntity::addOrderItem);
    }

}
