package com.example.labspring.catalog;

import com.example.labspring.product.Electronics;
import com.example.labspring.product.Food;
import com.example.labspring.product.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component //Объявляем бином с дефолтным scope singleton, тк не нужен более чем в одном экземпляре
public class Catalog {
    private final Map<String, Product> products = new HashMap<>();

    @PostConstruct //Инициализируем каталог дефолтными значениями при старте приложения
    private void initializeCatalog() {
        addProduct(new Food("Яблоко", 1.0, 0.2));
        addProduct(new Food("Банан", 1.5, 0.15));
        addProduct(new Electronics("Ноутбук", 50000.0, 2.0));
        addProduct(new Electronics("Телефон", 30000.0, 0.5));
    }

    public void addProduct(Product product) {
        products.put(product.getName(), product);
        System.out.println("Продукт '" + product.getName() + "' добавлен в каталог.");
    }

    public Product getProduct(String name) {
        return products.get(name);
    }

    public void displayProducts() {
        CustomMapIterator<String, Product> iterator = new CustomMapIterator<>(products);
        while (iterator.hasNext()) {
            iterator.next().displayInfo();
        }
    }
}
