package com.project.models_rework;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItem {
    Integer orderId;
    Integer productId;
    Integer quantity;
    Float price;
    Float discount;
    LocalDateTime createAt;
    LocalDateTime updateAt;
}
