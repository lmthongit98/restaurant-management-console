package com.tma.training.restaurant.infrastructure.persistences.mysql.adapters;

import com.tma.training.restaurant.commons.exceptions.EntityNotFoundException;
import com.tma.training.restaurant.domain.models.BillModel;
import com.tma.training.restaurant.domain.repositories.BillRepository;
import com.tma.training.restaurant.infrastructure.persistences.mysql.entities.BillEntity;
import com.tma.training.restaurant.infrastructure.persistences.mysql.entities.OrderItemEntity;
import com.tma.training.restaurant.infrastructure.persistences.mysql.mappers.impl.BillDataAccessMapper;
import com.tma.training.restaurant.infrastructure.persistences.mysql.mappers.impl.OrderItemDataAccessMapper;
import com.tma.training.restaurant.infrastructure.persistences.mysql.repositories.JpaBillRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class JpaBillRepositoryAdapter implements BillRepository {

    private final JpaBillRepository jpaBillRepository;
    private final BillDataAccessMapper billDataAccessMapper;
    private final OrderItemDataAccessMapper orderItemDataAccessMapper;

    @Override
    public Optional<BillModel> findById(UUID id) {
        return jpaBillRepository.findById(id).map(billDataAccessMapper::toModel);
    }

    @Override
    public void create(BillModel billModel) {
        jpaBillRepository.save(billDataAccessMapper.toEntity(billModel));
    }

    @Override
    public void update(BillModel billModel) {
        BillEntity existingEntity = jpaBillRepository.findById(billModel.getId()).orElseThrow(() -> new EntityNotFoundException("Bill", billModel.getId().toString()));
        updateBillEntity(existingEntity, billModel);
        jpaBillRepository.save(existingEntity);
    }

    @Override
    public void deleteById(UUID id) {
        jpaBillRepository.deleteById(id);
    }

    @Override
    public boolean existsById(UUID id) {
        return jpaBillRepository.existsById(id);
    }

    private void updateBillEntity(BillEntity billEntity, BillModel billModel) {
        billEntity.setUpdatedDate(billModel.getUpdatedDate());
        billEntity.getOrderItems().clear();
        List<OrderItemEntity> orderItemEntities = billModel.getOrderItems().stream().map(orderItemDataAccessMapper::toEntity).toList();
        orderItemEntities.forEach(billEntity::addOrderItem);
    }

}
