package com.example.labspring.console.command;

import com.example.labspring.cart.ShoppingFacade;
import org.springframework.stereotype.Component;

@Component //Объявляем бином с дефолтным scope singleton, тк не нужен более чем в одном экземпляре
public class RemoveProductCommand implements Command {
    private final ShoppingFacade shoppingFacade;

    //DI через параметры конструктора
    public RemoveProductCommand(ShoppingFacade shoppingFacade) {
        this.shoppingFacade = shoppingFacade;
    }

    //тк спринг не поддерживает создание бинов с не-бин параметрами, то имя продукта выносим в аргументы метода
    @Override
    public void execute(Object... atrs) {
        if (atrs.length < 1) {
            throw new IllegalArgumentException("Неверное число аргументов.");
        }
        shoppingFacade.getMainCart().removeProduct(atrs[0].toString());
    }
}