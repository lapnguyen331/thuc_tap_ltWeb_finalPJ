package com.project.models_rework;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product implements Serializable {
    Integer id;
    String name;
    Float price;
    Integer quantity;
    Integer minAge;
    Integer thumbnail;
    String specification;
    Float weight;
    Boolean status;
    String brand;
    String description;
    Integer producerId;
    Integer categoryId;
    Integer discountId;
    Integer blogId;
    LocalDateTime createAt;
    LocalDateTime updateAt;
}
