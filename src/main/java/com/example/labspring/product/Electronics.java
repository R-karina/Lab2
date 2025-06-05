package com.example.labspring.product;

/*
 * Класс для товаров категории "Электроника".
 * Реализует интерфейс Product и добавляет функционал гарантии.
 */
// Класс для товаров категории "Электроника"
public class Electronics implements Product {
    private String name;
    private double price;
    private double weight;
    private String warranty = "Нет гарантии";

    public Electronics(String name, double price, double weight) {
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

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public void displayInfo() {
        System.out.println("Электроника: " + name + ", Цена: " + price + ", Вес: " + weight + ", Гарантия: " + warranty);
    }
}