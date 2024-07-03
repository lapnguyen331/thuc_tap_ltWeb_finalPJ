package com.project.models;

import com.project.services.ProductService;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private final  Map<Integer, CartItem> products;

    public Cart() {
        this.products = new HashMap<>();
    }

    public Map<Integer, CartItem> getProducts() {
        return products;
    }

    public boolean put(Product product) {
        int productKey = product.getId();
        if (products.containsKey(productKey)) {
            return false;
        }
        this.products.put(productKey, new CartItem(product, 1));
        return true;
    }

    public boolean isContains(Product product) {
        int productKey = product.getId();
        return this.products.containsKey(productKey);
    }

    public boolean putOut(Product product, int quantity) {
        if (!isContains(product)) return false;
        if (getItem(product).getQuantity() - quantity <= 0) {
            this.products.remove(product.getId());
        }
        else {
            getItem(product).setQuantity(getItem(product).getQuantity() - quantity);
        }
        return true;
    }

    public void putIn(Product product, int quantity) {
        int productKey = product.getId();
        if (products.containsKey(productKey)) {
            getItem(product).setQuantity(getItem(product).getQuantity() + quantity);
            return;
        }
        this.products.put(productKey, new CartItem(product, quantity));
    }

    public CartItem getItem(Product product) {
        return this.products.get(product.getId());
    }

    public void remove(Product product) {
        int productKey = product.getId();
        this.products.remove(productKey);
    }
}
