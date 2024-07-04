package com.tma.training.restaurant;

public class RestaurantConsoleApp {

//    private static final MenuController menuController = MenuControllerImpl.getInstance();
//    private static final MenuView menuView = MenuView.getInstance();
//
//    public static void main(String[] args) {
//        System.out.println(UUID.randomUUID().toString());
//        Scanner scanner = new Scanner(System.in);
//        boolean isRunning = true;
//        while (isRunning) {
//            System.out.println("\n=== Restaurant Management ===");
//            System.out.println("1. Menu Management");
//            System.out.println("2. Bill Order Management");
//            System.out.println("0. Exit");
//            System.out.print("Enter your choice: ");
//            int choice = Integer.parseInt(scanner.nextLine());
//            switch (choice) {
//                case 1:
//                    manageMenu(scanner);
//                    break;
//                case 2:
//                    break;
//                case 0:
//                    isRunning = false;
//                    break;
//                default:
//                    System.out.println("Invalid choice. Please try again.");
//                    break;
//            }
//        }
//        scanner.close();
//        System.out.println("Exiting the application...");
//    }
//
//
//    private static void manageMenu(Scanner scanner) {
//        boolean isRunning = true;
//        while (isRunning) {
//            System.out.println("\u001B[32m" + "\n------ Menu Management ------" + "\u001B[0m");
//            System.out.println("1. Create Menu Item");
//            System.out.println("2. Retrieve Menu Item");
//            System.out.println("3. Update Menu Item");
//            System.out.println("4. Delete Menu Item");
//            System.out.println("5. List All Menu Items");
//            System.out.println("6. Search Menu Items");
//            System.out.println("0. Exit");
//            System.out.println("\u001B[32m" + "-------------------------------" + "\u001B[0m");
//            try {
//                System.out.print("Enter your choice: ");
//                int choice = Integer.parseInt(scanner.nextLine());
//                switch (choice) {
//                    case 1:
//                        createMenu(scanner);
//                        break;
//                    case 2:
//                        retrieveMenu(scanner);
//                        break;
//                    case 3:
//                        break;
//                    case 4:
//                        deleteMenu(scanner);
//                        break;
//                    case 5:
//                        listAllMenus();
//                        break;
//                    case 6:
//                        break;
//                    case 0:
//                        isRunning = false;
//                        break;
//                    default:
//                        System.out.println("Invalid choice. Please try again.");
//                }
//            } catch (Exception ex) {
//                ex.printStackTrace();
//                System.err.println(ex.getMessage());
//            }
//
//        }
//        System.out.println("Exiting the menu management...");
//    }
//
//    private static void retrieveMenu(Scanner scanner) {
//        listAllMenus();
//        String id = menuView.getMenuIdInput(scanner);
//        MenuDto menuDto = menuController.findById(id);
//        menuView.displayMenu(menuDto);
//    }
//
//    private static void listAllMenus() {
//        menuView.listAllMenus(menuController.findAll());
//    }
//
//    private static void createMenu(Scanner scanner) {
//        MenuDto menuDto = menuView.getMenuDtoInput(scanner);
//        menuController.create(menuDto);
//        System.out.println("Created successfully!");
//    }
//
//    private static void deleteMenu(Scanner scanner) {
//        listAllMenus();
//        String id = menuView.getMenuIdInput(scanner);
//        menuController.delete(id.trim());
//        System.out.println("Deleted successfully!");
//    }

}
