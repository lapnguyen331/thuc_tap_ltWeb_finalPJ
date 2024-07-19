package com.project.models_rework;

import com.project.models_rework.enums.OrderDetailsStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetails {
    Integer orderId;
    Integer productId;
    Integer quantity;
    Float price;
    OrderDetailsStatus status;
    LocalDateTime createAt;
    LocalDateTime updateAt;
}
