package com.example.labspring.product;

/*
 * Интерфейс для всех товаров в магазине.
 * Определяет обязательные методы, которые должны реализовать все продукты.
 */
public interface Product {
    String getName();         // Возвращает название товара
    double getPrice();        // Возвращает цену товара
    double getWeight();       // Возвращает вес товара
    void displayInfo();       // Выводит информацию о товаре
}