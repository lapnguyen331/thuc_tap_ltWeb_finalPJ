package com.project.web_socket.chat;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PUBLIC)
public class ReceiveChatDTO {
    Sender sender;
    Boolean isNew;
    String message;
    LocalDateTime timestamp;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @FieldDefaults(level = AccessLevel.PUBLIC)
    public static class Sender {
        String username;
    }

}
