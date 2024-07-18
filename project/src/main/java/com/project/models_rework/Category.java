package com.project.models_rework;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.annotation.ManagedBean;
import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Category implements Serializable {
    Integer id;
    String name;
    Integer thumbnail;
    Boolean status;
    Integer blogId;
    LocalDateTime createAt;
    LocalDateTime updateAt;
}
