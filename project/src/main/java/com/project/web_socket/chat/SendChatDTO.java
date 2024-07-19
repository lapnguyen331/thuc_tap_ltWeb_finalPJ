package com.project.web_socket.chat;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PUBLIC)
public class SendChatDTO {
    String message;
    LocalDateTime timestamp;
    Receiver receiver;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @FieldDefaults(level = AccessLevel.PUBLIC)
    public static class Receiver {
        String username;
    }

}
