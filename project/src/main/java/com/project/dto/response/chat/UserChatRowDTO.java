package com.project.dto.response.chat;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PUBLIC)
public class UserChatRowDTO implements Serializable {
    Sender sender;
    Receiver receiver;
    String message;
    LocalDateTime createAt;
    Boolean isNew;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @FieldDefaults(level = AccessLevel.PUBLIC)
    public static class Sender {
        Integer id;
        String username;
        String avatar;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @FieldDefaults(level = AccessLevel.PUBLIC)
    public static class Receiver {
        Integer id;
        String username;
        String avatar;
    }
}
