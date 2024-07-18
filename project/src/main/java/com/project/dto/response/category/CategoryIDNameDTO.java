package com.project.dto.response.category;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PUBLIC)
public class CategoryIDNameDTO {
    Integer id;
    String name;
}
