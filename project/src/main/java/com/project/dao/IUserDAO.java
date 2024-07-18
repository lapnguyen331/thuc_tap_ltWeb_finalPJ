package com.project.dao;

import com.project.models.User;
import org.json.JSONObject;

import java.io.LineNumberInputStream;
import java.util.List;

public interface IUserDAO {
    List<User> getAll();
    User getLoginInfo(String username);
    User getInforUserById(int id);
    User getUserByMail(String mail);
    User getToken(User user);
    int insert(User user);
    int validate(int id);
    int updateAccount(int id,String username,String password );
    int updateAccountById(int id, String password);
    int updateToken(int id, String token);
    int updateInfor(int id, String username, String fistname,String lastname, String email, String phone,String address, String gender, String birth) ;
    List<User> getAllCustomer();
    int updateCusIfor(int id, String username, String firstname,String lastname,String email,String phone,String address, String gender, String birth,String status);
}
