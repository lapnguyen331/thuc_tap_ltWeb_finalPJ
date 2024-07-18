package com.project.models_rework;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Discount {
    Integer id;
    String name;
    Float discountPercent;
    Boolean status;
    LocalDateTime startAt;
    LocalDateTime finishAt;
    LocalDateTime createAt;
    LocalDateTime updateAt;
}
