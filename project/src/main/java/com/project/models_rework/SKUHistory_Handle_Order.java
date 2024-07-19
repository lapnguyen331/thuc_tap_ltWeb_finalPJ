package com.project.models_rework;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SKUHistory_Handle_Order {
    Integer skuHistoryId;
    Integer orderId;
    Integer productId;
    Integer stockId;
    Float revenue;
    Integer quantity;
}
