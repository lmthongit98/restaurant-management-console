package com.tma.training.restaurant.application.controllers.menu;

import com.tma.training.restaurant.domain.models.MenuModel;
import com.tma.training.restaurant.domain.usecases.menu.GetAllMenusUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/menus")
@RequiredArgsConstructor
public class GetAllMenuController {

    private final GetAllMenusUseCase getAllMenusUseCase;

    @GetMapping
    public ResponseEntity<List<MenuModel>> getAllMenus() {
        List<MenuModel> menus = getAllMenusUseCase.getAllMenus();
        return ResponseEntity.ok(menus);
    }

}
