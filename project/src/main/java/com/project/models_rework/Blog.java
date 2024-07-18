package com.project.models_rework;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Blog {
    Integer id;
    Integer userId;
    String title;
    Boolean status;
    String description;
    String path;
    Integer thumbnail;
    LocalDateTime createAt;
    LocalDateTime updateAt;
}
