package com.example.labspring.console;

import com.example.labspring.cart.ShoppingFacade;
import com.example.labspring.cart.UnifiedCart;
import com.example.labspring.console.aspect.HandleUserErrors;
import com.example.labspring.console.command.RemoveProductCommand;
import com.example.labspring.console.error.UserInputException;
import com.example.labspring.product.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;


@Component //Объявляем бином с дефолтным scope singleton, тк не нужен более чем в одном экземпляре
@HandleUserErrors //Аннотация для аспекта
public class CustomerActions {
    private final ShoppingFacade shoppingFacade;
    private final RemoveProductCommand removeProductCommand;
    private final ScannerFacade scannerFacade;
    private UnifiedCart currentCart;
    private UnifiedCart mainCart;

    public CustomerActions(ShoppingFacade shoppingFacade, RemoveProductCommand removeProductCommand, ScannerFacade scannerFacade) {
        this.shoppingFacade = shoppingFacade;
        this.removeProductCommand = removeProductCommand;
        this.scannerFacade = scannerFacade;
    }

    @PostConstruct //Инициализируем корзины после старта приложения
    public void init() {
        mainCart = shoppingFacade.getMainCart();
        currentCart = mainCart;
    }


    public void addToCart() {
        System.out.printf("Введите название продукта для корзины '%s': ", currentCart.getName());
        String name = scannerFacade.getString();
        Product product = shoppingFacade.getProductFromCatalog(name);
        if (product == null) {
            throw new UserInputException("Продукт не найден.");
        }
        currentCart.addProduct(product);
    }


    public void removeFromCart() {
        System.out.println("Введите название продукта для удаления:");
        String name = scannerFacade.getString();
        removeProductCommand.execute(name);
    }


    public void showCatalog() {
        shoppingFacade.displayCatalog();
    }


    public void showCart() {
        mainCart.display();
        System.out.println("Итоговая сумма: " + mainCart.calculateTotalPrice());
        System.out.println("Общий вес: " + mainCart.calculateTotalWeight());
    }


    public void createSubCart() {
        System.out.println("Введите имя подкорзины:");
        String name = scannerFacade.getString();
        if (mainCart.getByName(name) != null) {
            throw new UserInputException("Такая подкорзина уже существует.");
        }
        currentCart = shoppingFacade.createSubCart(name);
        System.out.println("Создана подкорзина: " + name);
    }


    public void switchCart() {
        System.out.println("Введите имя корзины (main — для основной):");
        String name = scannerFacade.getString();
        if ("main".equals(name)) {
            currentCart = mainCart;
            return;
        }
        UnifiedCart target = mainCart.getByName(name);
        if (target == null) {
            throw new UserInputException("Корзина не найдена.");
        }
        currentCart = target;
    }
}
