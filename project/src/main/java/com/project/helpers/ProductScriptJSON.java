package com.project.helpers;

import com.google.gson.Gson;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PUBLIC)
public class ProductScriptJSON {
    String thumbnail;
    String name;
    Float price;
    String specification;
    Integer minAge;
    String brand;
    String description;
    String blog;
    Integer quantity;
    Integer status;
    Float weight;
    Integer discountId;
    Integer producerId;
    Integer categoryId;
    List<String> galleries;
}
