package com.project.dto.response.product;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PUBLIC)
public class ProductDetailsDTO {
    Integer id;
    String name;
    Float price;
    Discount discount;
    String priceFormat;
    String discountPriceFormat;
    String thumbnail;
    Integer categoryId;
    String brand;
    String blogPath;
    List<String> gallery;

    @Builder
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @FieldDefaults(level = AccessLevel.PUBLIC)
    public static class Discount {
        Integer id;
        String name;
        Float percent;
    }
}
