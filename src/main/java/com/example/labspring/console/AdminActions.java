package com.example.labspring.console;

import com.example.labspring.cart.ShoppingFacade;
import com.example.labspring.console.aspect.HandleUserErrors;
import com.example.labspring.console.error.UserInputException;
import com.example.labspring.product.Electronics;
import com.example.labspring.product.Food;
import com.example.labspring.product.Product;
import org.springframework.stereotype.Component;

@Component //Объявляем бином с дефолтным scope singleton, тк не нужен более чем в одном экземпляре
@HandleUserErrors //Аннотация для аспекта
public class AdminActions {
    private final ShoppingFacade facade;
    private final ScannerFacade scannerFacade;

    //DI
    public AdminActions(ShoppingFacade facade, ScannerFacade scannerFacade1) {
        this.facade = facade;
        this.scannerFacade = scannerFacade1;
    }

    public void addProduct() throws UserInputException {
        System.out.println("Введите тип продукта (1 - еда, 2 - электроника):");
        Integer type = scannerFacade.getInt();

        if (type == null || type != 1 && type != 2) {
            throw new UserInputException("Неверный тип.");
        }

        System.out.println("Введите название:");
        String name = scannerFacade.getString();
        System.out.println("Введите цену:");
        double price = scannerFacade.getPositiveDouble();
        System.out.println("Введите вес:");
        double weight = scannerFacade.getPositiveDouble();

        Product product = (type == 1) ? new Food(name, price, weight) : new Electronics(name, price, weight);
        facade.addProductToCatalog(product);
        System.out.println("Продукт добавлен.");
    }

    public void showCatalog() {
        facade.displayCatalog();
    }
}
