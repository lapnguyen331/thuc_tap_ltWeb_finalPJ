package com.project.models_rework;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    Integer id;
    String username;
    String password;
    Integer avatar;
    Integer levelAccess;
    String firstName;
    String lastName;
    Boolean gender;
    String address;
    String phone;
    LocalDate birth;
    Integer status;
    String email;
    Boolean verified;
    LocalDateTime createAt;
    LocalDateTime updateAt;
    String token;
    LocalDateTime tokenCreateAt;

    //ẩn bớt thông tin mail + phone
    public String hiddenInfor(String in){
        char[] em = in.toCharArray();
        for (int i = 3; i < em.length -3; i++) {
            em[i]='*';
        }
        return String.valueOf(em);
    }

    public String getFullName(String last, String first){
        if(last.isEmpty() && first.isEmpty()) return "";
        return last+" "+first;
    }
    public String getGender(int gender){
        return gender == 0?"nữ":"nam";
    }
    public String conGender(boolean gender){
        return gender == true ?"1":"0";
    }
    public Date convertToDateViaInstant(LocalDate dateToConvert) {
        return Date.from(dateToConvert.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    public static String hashPassword(String str) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(StandardCharsets.UTF_8.encode(str));
        return String.format("%032x", new BigInteger(1, md5.digest()));
    }
}
