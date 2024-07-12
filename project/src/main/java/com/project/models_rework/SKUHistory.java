package com.project.models_rework;

import com.project.models_rework.enums.SKUChangeType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SKUHistory {
    Integer id;
    Integer stockId;
    Integer changeValue;
    SKUChangeType type;
    String note;
    LocalDateTime createAt;
}
