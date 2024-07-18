package com.project.dto.response.product;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PUBLIC)
public class ProductCardDTO {
    Integer id;
    String name;
    Float price;
    Float discountPrice;
    String priceFormat;
    String discountPriceFormat;
    String thumbnail;
    Float discountPercent;
    Integer categoryId;
}
