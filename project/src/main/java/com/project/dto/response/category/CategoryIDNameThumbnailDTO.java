package com.project.dto.response.category;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PUBLIC)
public class CategoryIDNameThumbnailDTO {
    Integer id;
    String name;
    String thumbnail;
}
