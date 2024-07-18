package com.project.models_rework;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashMap;
import java.util.Map;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Cart {
    Integer cartId;
    Integer userId;
    Map<Integer, CartItem> products = new HashMap<>();

    public boolean put(Integer productId, Integer quantity) {
        if (products.containsKey(productId)) {
            return false;
        }
        this.products.put(productId, new CartItem(productId, quantity));
        return true;
    }

    public boolean isContains(Integer productId) {
        return this.products.containsKey(productId);
    }

    public CartItem getItem(Integer productId) {
        return this.products.get(productId);
    }

    public void remove(Integer productId) {
        this.products.remove(productId);
    }
}
