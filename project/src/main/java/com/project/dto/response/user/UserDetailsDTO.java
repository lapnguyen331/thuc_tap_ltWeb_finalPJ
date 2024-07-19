package com.project.dto.response.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDetailsDTO {
    Integer id;
    String username;
    String firstName;
    String lastName;
    String address;
    String email;
    Boolean gender;
    String avatar;
}
