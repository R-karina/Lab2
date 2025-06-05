package com.example.labspring.console;

import com.example.labspring.console.error.UserInputException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component //Объявляем бином с дефолтным scope singleton, тк не нужен более чем в одном экземпляре
public class ConsoleMenu implements CommandLineRunner { //Интерфейс для запуска консольной работы в спринг (после создания бинов)
    private final AdminActions adminActions;
    private final CustomerActions customerActions;
    private final ScannerFacade scannerFacade;

    //DI
    public ConsoleMenu(AdminActions adminActions, CustomerActions customerActions, ScannerFacade scannerFacade) {
        this.adminActions = adminActions;
        this.customerActions = customerActions;
        this.scannerFacade = scannerFacade;
    }

    @Override //Запускается автоматом при старте спринг-контекста
    public void run(String... args) {
        while (true) {
            System.out.println("Выберите режим: 1 - Админ, 2 - Покупатель, 0 - Выход");
            Integer mode = scannerFacade.getInt();
            System.out.println();
            switch (mode) {
                case 0 -> {
                    return;
                }
                case 1 -> adminLoop();
                case 2 -> customerLoop();
                default -> System.out.println("Неверный режим.");
            }
        }
    }

    //Аспект не работает при self-вызовах, поэтому выносим логику в отдельные бины и вызываем их в цикле
    public void adminLoop() {
        while (true) {
            System.out.println("1 - Добавить продукт, 2 - Показать каталог, 0 - Назад");
            int opt = scannerFacade.getInt();
            switch (opt) {
                case 0 -> {
                    return;
                }
                case 1 -> adminActions.addProduct();
                case 2 -> adminActions.showCatalog();
                default -> System.out.println("Неверный вариант.");
            }
            System.out.println();
        }
    }

    public void customerLoop() {
        while (true) {
            System.out.println("""
                    1 - Добавить продукт
                    2 - Удалить продукт
                    3 - Показать каталог
                    4 - Просмотр корзины
                    5 - Добавить подкорзину
                    6 - Сменить корзину
                    0 - Назад""");
            Integer opt = scannerFacade.getInt();
            switch (opt) {
                case 0 -> {
                    return;
                }
                case 1 -> customerActions.addToCart();
                case 2 -> customerActions.removeFromCart();
                case 3 -> customerActions.showCatalog();
                case 4 -> customerActions.showCart();
                case 5 -> customerActions.createSubCart();
                case 6 -> customerActions.switchCart();
                default -> throw new UserInputException("Неверный вариант.");
            }
            System.out.println();
        }
    }

}
