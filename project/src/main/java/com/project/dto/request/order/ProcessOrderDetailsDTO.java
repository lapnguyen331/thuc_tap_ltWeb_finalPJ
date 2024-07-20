package com.project.dto.request.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PUBLIC)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProcessOrderDetailsDTO {
    Integer orderId;
    Integer stockId;
    Integer quantity;
}
