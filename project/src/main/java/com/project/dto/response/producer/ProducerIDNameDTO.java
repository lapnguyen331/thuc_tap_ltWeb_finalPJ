package com.project.dto.response.producer;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PUBLIC)
public class ProducerIDNameDTO {
    Integer id;
    String name;
}
