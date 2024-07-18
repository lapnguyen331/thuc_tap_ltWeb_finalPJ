package com.project.models_rework;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Chat {
    Integer id;
    Integer senderId;
    Integer receiverId;
    String message;
    LocalDateTime createAt;
    Boolean isNew;
}
