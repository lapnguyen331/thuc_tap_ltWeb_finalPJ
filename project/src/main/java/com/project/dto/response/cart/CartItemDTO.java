package com.project.dto.response.cart;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PUBLIC)
public class CartItemDTO {
    CartItemDTO.Product product;
    Integer quantity;
    Float price;
    String formatPrice;
    Float discountPrice;
    String formatDiscountPrice;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @FieldDefaults(level = AccessLevel.PUBLIC)
    public static class Product {
        Integer id;
        String name;
        String specification;
        String brand;
        String categoryName;
        Float price;
        String formatPrice;
        Float discountPrice;
        String formatDiscountPrice;
        String thumbnail;
    }
}
