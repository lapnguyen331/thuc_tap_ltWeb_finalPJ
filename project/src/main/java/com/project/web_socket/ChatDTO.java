package com.project.web_socket;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PUBLIC)
public class ChatDTO {
    Sender sender;
    Receiver receiver;
    Status status;
    String message;
    List<String> actions;
    List<ChatDTO> history;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @FieldDefaults(level = AccessLevel.PUBLIC)
    public static class Sender {
        String username;
        String message;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @FieldDefaults(level = AccessLevel.PUBLIC)
    public static class Receiver {
        String username;
        String message;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @FieldDefaults(level = AccessLevel.PUBLIC)
    public static class Status {
        Boolean isActive;
    }
}
