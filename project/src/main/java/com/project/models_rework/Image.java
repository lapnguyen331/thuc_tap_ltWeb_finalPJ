package com.project.models_rework;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Image {
    Integer id;
    String path;
    String uuid;
    LocalDateTime createAt;
    LocalDateTime updateAt;
}
