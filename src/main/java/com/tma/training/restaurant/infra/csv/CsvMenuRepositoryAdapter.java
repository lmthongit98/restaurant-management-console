package com.tma.training.restaurant.infra.csv;

import com.tma.training.restaurant.domain.models.MenuModel;
import com.tma.training.restaurant.domain.repositories.MenuRepository;
import com.tma.training.restaurant.infra.csv.entity.Menu;
import com.tma.training.restaurant.infra.csv.repository.CsvMenuRepository;
import com.tma.training.restaurant.infra.csv.repository.impl.CsvMenuRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Primary
@Repository
@RequiredArgsConstructor
public class CsvMenuRepositoryAdapter implements MenuRepository {

    private final CsvMenuRepository csvMenuRepository = CsvMenuRepositoryImpl.getInstance();

    @Override
    public List<MenuModel> findAll() {
        return csvMenuRepository.findAll().stream().map(this::toModel).toList();
    }

    private MenuModel toModel(Menu menu) {
        return MenuModel.builder()
                .id(UUID.fromString(menu.getId()))
                .name(menu.getName())
                .description(menu.getDescription())
                .price(BigDecimal.valueOf(menu.getPrice()))
                .additionalDetails(menu.getAdditionalDetails())
                .build();
    }

    @Override
    public Optional<MenuModel> findById(UUID id) {
        return Optional.empty();
    }

    @Override
    public void create(MenuModel menuModel) {

    }

    @Override
    public void update(MenuModel menuModel) {

    }

    @Override
    public void deleteById(UUID id) {

    }

    @Override
    public boolean existsById(UUID menuId) {
        return false;
    }

}
