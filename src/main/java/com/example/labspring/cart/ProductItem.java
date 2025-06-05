package com.example.labspring.cart;


import com.example.labspring.product.Product;

// Листовой элемент для продуктов нужно создавать каждый раз заново, при этом не нужен в DI и имеет кастомный конструктор - поэтому не бин
class ProductItem implements CartComponent {
    private Product product;

    public ProductItem(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    @Override
    public void display() {
        product.displayInfo();
    }
}