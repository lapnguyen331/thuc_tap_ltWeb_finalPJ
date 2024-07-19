package com.project.web_socket;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PUBLIC)
public class WSUserRequestDTO {
    String action;
    String data;
    String target;
}
