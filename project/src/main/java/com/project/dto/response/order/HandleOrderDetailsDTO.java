package com.project.dto.response.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PUBLIC)
public class HandleOrderDetailsDTO {
    Integer stockId;
    Integer orderId;
    String productName;
    String thumbnail;
    Integer quantity;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    LocalDate expiredDate;
}
