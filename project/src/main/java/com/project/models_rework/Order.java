package com.project.models_rework;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order {
    Integer id;
    Integer userId;
    Float totalPrice;
    Integer status;
    String receiverName,
            receiverAddress,
            receiverPhone,
            receiverEmail;
    LocalDateTime createAt;
    LocalDateTime updateAt;
}
