package com.project.dto.response.product;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PUBLIC)
public class ProductIDNameThumbnailDTO {
    Integer id;
    String name;
    String thumbnail;
}
