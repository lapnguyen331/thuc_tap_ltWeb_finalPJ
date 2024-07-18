package com.project.dto.response.stock;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.models_rework.enums.SKUChangeType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PUBLIC)
public class SKUHistoryDTO implements Serializable {
    Integer id;
    Integer prevValue;
    Integer changeValue;
    Product product;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    LocalDateTime createAt;
    SKUChangeType type;
    String note;
    OrderItem orderItem;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @FieldDefaults(level = AccessLevel.PUBLIC)
    public static class Product {
        Integer id;
        String name;
        String thumbnail;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @FieldDefaults(level = AccessLevel.PUBLIC)
    public static class OrderItem {
        Integer id;
        Integer orderId;
    }
}
