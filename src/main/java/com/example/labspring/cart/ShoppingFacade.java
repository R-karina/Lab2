package com.example.labspring.cart;

import com.example.labspring.catalog.Catalog;
import com.example.labspring.product.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

@Component
public class ShoppingFacade {
    private final Catalog catalog;
    private final ObjectProvider<UnifiedCart> cartProvider; //Провайдер для получения прототайп бинов
    private UnifiedCart mainCart;

    //DI
    public ShoppingFacade(Catalog catalog, ObjectProvider<UnifiedCart> cartProvider) {
        this.catalog = catalog;
        this.cartProvider = cartProvider;
    }

    @PostConstruct //Добавляем метод для создания основной корзины при старте приложения
    public void createMainCart() {
        mainCart = cartProvider.getObject();
        mainCart.setName("Основная корзина");
    }

    public void addProductToCatalog(Product product) {
        catalog.addProduct(product);
    }

    public UnifiedCart createSubCart(String name) {
        UnifiedCart subCart = cartProvider.getObject(); //создаем подкорзину-бин через провайдер прототайп бинов
        subCart.setName(name);
        mainCart.addSubCart(subCart);
        return subCart;
    }

    public UnifiedCart getMainCart() {
        return mainCart;
    }

    public Product getProductFromCatalog(String name) {
        return catalog.getProduct(name);
    }

    public void displayCatalog() {
        System.out.println("Продукты в каталоге:");
        catalog.displayProducts();
    }

}
