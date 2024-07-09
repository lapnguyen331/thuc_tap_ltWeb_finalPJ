package com.project.dao;

import com.project.models.Cart;


import java.util.List;

public interface ICartDAO {
    List<Cart> getAll();
    Cart updateCart(int id);
//    User getInforUserById(int id);
//    List<User> getUserByMail(String mail);
//    User getToken(User user);
//    int insert(User user);
//    int validate(int id);
//    int updateAccount(int id,String username,String password );
//    int updateAccountById(int id, String password);
//    int updateToken(int id, String token);
//    int updateInfor(int id, String username, String fistname,String lastname, String email, String phone,String address, String gender, String birth) ;
}
