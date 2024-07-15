package com.project.dto.request.stock;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.models_rework.enums.SKUChangeType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PUBLIC)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SelectIDsDTO implements Serializable {
    List<Integer> stockIds;
}
