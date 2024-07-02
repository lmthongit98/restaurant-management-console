package com.tma.training.restaurant.infra.mysql.adapters;

import com.tma.training.restaurant.domain.models.MenuModel;
import com.tma.training.restaurant.domain.repositories.MenuRepository;
import com.tma.training.restaurant.infra.mysql.MenuJpaRepository;
import com.tma.training.restaurant.infra.mysql.mapper.impl.MenuDataAccessMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class MenuRepositoryImpl implements MenuRepository {

    private final MenuJpaRepository menuJpaRepository;
    private final MenuDataAccessMapper menuDataAccessMapper;

    @Override
    public List<MenuModel> findAll() {
        return menuJpaRepository.findAll().stream().map(menuDataAccessMapper::toModel).toList();
    }

    @Override
    public Optional<MenuModel> findById(UUID id) {
        return menuJpaRepository.findById(id).map(menuDataAccessMapper::toModel);
    }

    @Override
    public void create(MenuModel menuModel) {
        menuJpaRepository.save(menuDataAccessMapper.toEntity(menuModel));
    }

    @Override
    public void update(MenuModel menuModel) {
        menuJpaRepository.save(menuDataAccessMapper.toEntity(menuModel));
    }

    @Override
    public void deleteById(UUID id) {
        menuJpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(UUID menuId) {
        return menuJpaRepository.existsById(menuId);
    }

}
