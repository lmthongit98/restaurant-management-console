package com.tma.training.restaurant.view;

import com.tma.training.restaurant.dtos.response.MenuDto;

import java.util.List;
import java.util.Scanner;

public class MenuView {

    private static MenuView instance;

    public static MenuView getInstance() {
        if (instance == null) {
            instance = new MenuView();
        }
        return instance;
    }

    public void listAllMenus(List<MenuDto> menuItems) {
        if (menuItems.isEmpty()) {
            System.out.println("No menu items found.");
        } else {
            System.out.println("\n--------------------------------Menu Items--------------------------------");
            for (MenuDto menuItem : menuItems) {
                System.out.println(" \t+ " + menuItem);
            }
            System.out.println("--------------------------------------------------------------------------\n");
        }
    }

    public MenuDto getMenuDtoInput(Scanner scanner) {
        String name = getInput("Name", scanner);
        String description = getInput("Description", scanner);
        System.out.print("Image: ");
        String image = scanner.nextLine();
        System.out.print("Price: ");
        double price = Double.parseDouble(scanner.nextLine());

        System.out.print("Additional Details: ");
        String additionalDetails = scanner.nextLine();
        return MenuDto.builder()
                .name(name)
                .image(image)
                .description(description)
                .price(price)
                .additionalDetails(additionalDetails)
                .build();
    }

    public void displayMenu(MenuDto menuDto) {
        System.out.println("ID: " + menuDto.getId() + " \nName: " + menuDto.getName()
                + " \nDescription: " + menuDto.getDescription() + " \nPrice: " + menuDto.getPrice()
                +" \nAdditional details: " + menuDto.getAdditionalDetails());
    }

    private  String getInput(String fieldName, Scanner scanner) {
        String input;
        do {
            System.out.print(fieldName + ": ");
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println(fieldName + " cannot be left blank. Please re-enter.");
            }
        } while (input.isEmpty());
        return input;
    }

    public String getMenuIdInput(Scanner scanner) {
        return getInput("Enter id to delete menu", scanner);
    }
}
