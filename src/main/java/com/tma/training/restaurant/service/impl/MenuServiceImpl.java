package com.tma.training.restaurant.service.impl;

import com.tma.training.restaurant.common.exceptions.EntityNotFoundException;
import com.tma.training.restaurant.common.exceptions.MenuUpdateException;
import com.tma.training.restaurant.common.mapper.impl.MenuMapper;
import com.tma.training.restaurant.dto.MenuDto;
import com.tma.training.restaurant.entity.Menu;
import com.tma.training.restaurant.repository.MenuRepository;
import com.tma.training.restaurant.repository.impl.MenuRepositoryImpl;
import com.tma.training.restaurant.service.BillService;
import com.tma.training.restaurant.service.MenuService;

import java.util.List;
import java.util.stream.Collectors;

public class MenuServiceImpl implements MenuService {

    private static MenuServiceImpl instance;
    private final MenuMapper mapper;

    private final MenuRepository repository;
    private BillService billService;

    static {
        instance = new MenuServiceImpl();
    }

    private BillService getBillService() {
        if (billService == null) {
            billService = BillServiceImpl.getInstance();
        }
        return billService;
    }

    private MenuServiceImpl() {
        repository = MenuRepositoryImpl.getInstance();
        mapper = MenuMapper.getInstance();
    }

    public static MenuServiceImpl getInstance() {
        if (instance == null) {
            instance = new MenuServiceImpl();
        }
        return instance;
    }

    @Override
    public List<MenuDto> findAll() {
        List<Menu> menus = repository.findByDeleted(false);
        return menus.stream().map(mapper::mapToDto).collect(Collectors.toList());
    }

    @Override
    public MenuDto findById(String id) {
        Menu menu = findMenuById(id);
        return mapper.mapToDto(menu);
    }

    @Override
    public MenuDto create(MenuDto menuDto) {
        Menu menu = mapper.mapToEntity(menuDto);
        return mapper.mapToDto(repository.save(menu));
    }

    @Override
    public MenuDto update(MenuDto menuDto) {
        if (getBillService().checkMenuInUnpaidBill(menuDto.getId())) {
            throw new MenuUpdateException("Cannot update a menu in an unpaid bill.");
        }
        Menu menu = findMenuById(menuDto.getId());
        menu.setDescription(menuDto.getDescription());
        menu.setAdditionalDetails(menuDto.getAdditionalDetails());
        menu.setPrice(menuDto.getPrice());
        menu.setImage(menuDto.getImage());
        menu.setName(menuDto.getName());
        return mapper.mapToDto(repository.save(menu));
    }

    public Menu findMenuById(String id) {
        return repository.findByIdAndDeleted(id, false).orElseThrow(() -> new EntityNotFoundException("Menu", id));
    }

    @Override
    public void delete(String id) {
        Menu menu = findMenuById(id);
        menu.setDeleted(true);
        repository.save(menu);
    }

}
