package com.example.labspring.cart;

import com.example.labspring.product.Product;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component //Делаем бином-прототайпом чтобы создавался объект каждый раз заново
@Scope("prototype")
public class UnifiedCart implements CartComponent {
    private String name;
    private final List<CartComponent> components = new ArrayList<>();

    public void addProduct(Product product) {
        components.add(new ProductItem(product));
        System.out.println("Продукт '" + product.getName() + "' добавлен в корзину.");
    }

    public void addSubCart(UnifiedCart subCart) {
        components.add(subCart);
        System.out.println("Подкорзина '" + subCart.name + "' добавлена в корзину.");
    }

    public void removeProduct(String productName) {
        components.removeIf(component -> {
            if (component instanceof ProductItem) {
                ProductItem item = (ProductItem) component;
                return item.getProduct().getName().equalsIgnoreCase(productName);
            }
            return false;
        });
    }

    @Override
    public void display() {
        System.out.println("---");
        System.out.println(name + ":");
        if (components.isEmpty()) {
            System.out.println("Корзина пуста.");
        } else {
            for (CartComponent component : components) {
                component.display();
            }
        }
        System.out.println("---");
    }

    public UnifiedCart getByName(String targetName) {
        if (this.name.equals(targetName)) {
            return this;
        }

        for (CartComponent component : components) {
            if (component instanceof UnifiedCart cart) {
                UnifiedCart result = cart.getByName(targetName);
                if (result != null) {
                    return result;
                }
            }
        }

        return null;
    }

    public double calculateTotalPrice() {
        double totalPrice = 0.0;

        for (CartComponent component : components) {
            if (component instanceof ProductItem) {
                totalPrice += ((ProductItem) component).getProduct().getPrice();
            } else if (component instanceof UnifiedCart) {
                totalPrice += ((UnifiedCart) component).calculateTotalPrice(); // Рекурсивно добавляем вес подкорзин
            }
        }

        return totalPrice;
    }

    public double calculateTotalWeight() {
        double totalWeight = 0.0;

        for (CartComponent component : components) {
            if (component instanceof ProductItem) {
                totalWeight += ((ProductItem) component).getProduct().getWeight();
            } else if (component instanceof UnifiedCart) {
                totalWeight += ((UnifiedCart) component).calculateTotalWeight(); // Рекурсивно добавляем вес подкорзин
            }
        }

        return totalWeight;
    }

    public String getName() {
        return name;
    }

    //Делаем package-private сеттер чтобы им пользовался только фасад
    void setName(String name) {
        this.name = name;
    }
}
