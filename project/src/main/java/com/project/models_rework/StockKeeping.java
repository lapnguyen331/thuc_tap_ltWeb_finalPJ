package com.project.models_rework;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StockKeeping {
    Integer id;
    Integer productId;
    LocalDate expiredDate;
    Float unitPrice;
}
