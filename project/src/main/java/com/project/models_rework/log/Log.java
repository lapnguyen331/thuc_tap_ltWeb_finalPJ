package com.project.models_rework.log;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Log {
    Integer id;
    String level;
    String message;
    LocalDateTime logTime;
}
