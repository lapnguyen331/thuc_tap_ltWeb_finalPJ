package com.project.models_rework;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Producer {
    Integer id;
    String name;
    Boolean status;
    String address;
    String email;
    String phone;
    String taxCode;
    LocalDateTime createAt;
    LocalDateTime updateAt;
}
