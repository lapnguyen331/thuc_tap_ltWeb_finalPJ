package com.project.models;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

public class User {
    private int id;
    private String username;
    private String password;
    private Image avatar;
    private int levelAccess;
    private String firstName;
    private String lastName;
    private boolean gender;
    private String address;
    private String phone;
    private Date birthDate;
    private int status;
    private String email;
    private boolean verified;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private String token;
    private LocalDateTime tokenCreateAt;

    public User(int id, String username, String password, Image avatar, int levelAccess, String firstName, String lastName, boolean gender, String address, String phone, Date birthDate, int status, String email, boolean verified, LocalDateTime createAt, LocalDateTime updateAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.status = status;
        this.email = email;
        this.avatar = avatar;
        this.levelAccess = levelAccess;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
        this.birthDate = birthDate;
        this.verified = verified;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public User(int id, String username, String password, Image avatar, int levelAccess, String firstName, String lastName, boolean gender, String address, String phone, Date birthDate, int status, String email, boolean verified, LocalDateTime createAt, LocalDateTime updateAt, String token, LocalDateTime tokenCreateAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.avatar = avatar;
        this.levelAccess = levelAccess;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
        this.birthDate = birthDate;
        this.status = status;
        this.email = email;
        this.verified = verified;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.token = token;
        this.tokenCreateAt = tokenCreateAt;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getTokenCreateAt() {
        return tokenCreateAt;
    }

    public void setTokenCreateAt(LocalDateTime tokenCreateAt) {
        this.tokenCreateAt = tokenCreateAt;
    }

    public User() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Image getAvatar() {
        return avatar;
    }

    public void setAvatar(Image avatar) {
        this.avatar = avatar;
    }

    public int getLevelAccess() {
        return levelAccess;
    }

    public void setLevelAccess(int levelAccess) {
        this.levelAccess = levelAccess;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }
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
        return last+first;
    }
    public String getGender(int gender){
        return gender == 0?"nữ":"nam";
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", avatar=" + avatar +
                ", levelAccess=" + levelAccess +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", birthDate=" + birthDate +
                ", status=" + status +
                ", email='" + email + '\'' +
                ", verified=" + verified +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }

    public static String hashPassword(String str) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(StandardCharsets.UTF_8.encode(str));
        return String.format("%032x", new BigInteger(1, md5.digest()));
    }

    public static String getUUID() {
        return UUID.randomUUID().toString();
    }

    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
//        System.out.println(User.hashPassword("0919"));
//        System.out.println(new User().hiddenInfor("haudau124@gmail.com"));
    }
}
