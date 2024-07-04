package com.tma.training.restaurant.infrastructures.mysql.adapters;

import com.tma.training.restaurant.commons.exceptions.EntityNotFoundException;
import com.tma.training.restaurant.domain.models.BillModel;
import com.tma.training.restaurant.domain.models.OrderItemModel;
import com.tma.training.restaurant.domain.repositories.BillRepository;
import com.tma.training.restaurant.infrastructures.mysql.repositories.BillJpaRepository;
import com.tma.training.restaurant.infrastructures.mysql.entities.BillEntity;
import com.tma.training.restaurant.infrastructures.mysql.entities.OrderItemEntity;
import com.tma.training.restaurant.infrastructures.mysql.mappers.impl.BillDataAccessMapper;
import com.tma.training.restaurant.infrastructures.mysql.mappers.impl.OrderItemDataAccessMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    private void updateBillEntity(BillEntity existingEntity, BillModel billModel) {
        existingEntity.setUpdatedDate(billModel.getUpdatedDate());

        // Remove items that are not in the updated bill
        existingEntity.getOrderItems().removeIf(orderItem -> billModel.getOrderItems().stream().noneMatch(updatedItem -> updatedItem.getMenu().getId().equals(orderItem.getId())));

        // Update or add new items
        Map<UUID, OrderItemEntity> existingOrderItemsMap = existingEntity.getOrderItems().stream().collect(Collectors.toMap(OrderItemEntity::getId, Function.identity()));
        for (OrderItemModel orderItemModel : billModel.getOrderItems()) {
            OrderItemEntity orderItemEntity = existingOrderItemsMap.get(orderItemModel.getId());
            if (orderItemEntity != null) { // existing item
                orderItemEntity.setQuantity(orderItemModel.getQuantity());
            } else { // new item
                OrderItemEntity newOrderItemEntity = orderItemDataAccessMapper.toEntity(orderItemModel);
                existingEntity.addOrderItem(newOrderItemEntity);
            }
        }
    }

}
