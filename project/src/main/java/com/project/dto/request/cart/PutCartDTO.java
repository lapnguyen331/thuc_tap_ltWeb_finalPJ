package com.project.dto.request.cart;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.models_rework.enums.SKUChangeType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PUBLIC)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PutCartDTO implements Serializable {
    Integer productId;
    Integer quantity;
}
