package com.project.models_rework;

import com.project.models_rework.enums.SKUChangeType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StockKeepingHistory {
    Integer id;
    Integer stockId;
    Integer change;
    SKUChangeType type;
    String note;
    LocalDateTime createAt;
}
