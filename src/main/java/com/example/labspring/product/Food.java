package com.example.labspring.product;

/*
 * Класс для товаров категории "Еда".
 * Реализует интерфейс Product.
 */
public class Food implements Product {
    private String name;
    private double price;
    private double weight;

    public Food(String name, double price, double weight) {
        this.name = name;
        this.price = price;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getWeight() {
        return weight;
    }

    public void displayInfo() {
        System.out.println("Продукт: " + name + ", Цена: " + price + ", Вес: " + weight);
    }
}
