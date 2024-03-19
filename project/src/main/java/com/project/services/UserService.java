package com.project.services;

import com.project.dao.IUserDAO;
import com.project.dao.implement.FactoryDAO;
import com.project.dao.implement.UserDAO;
import com.project.db.JDBIConnector;
import com.project.exceptions.AlreadyVerifiedException;
import com.project.exceptions.DuplicateInfoUserException;
import com.project.exceptions.NotFoundUserException;
import com.project.models.User;
import org.jdbi.v3.core.Handle;

import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class UserService extends AbstractService {
    private final IUserDAO userDAO;
    public UserService() {
        super();
        this.userDAO = FactoryDAO.getDAO(super.handle, FactoryDAO.DAO_USER);
    }

    public UserService(Handle handle) {
        super(handle);
        this.userDAO = FactoryDAO.getDAO(super.handle, FactoryDAO.DAO_USER);
    }

    public List<User> getAll() {
        return userDAO.getAll();
    }

    public User getUserByName(String username) {
        return userDAO.getLoginInfo(username);
    }
    //lấy thông tin user + avartar bằng id
    public User getInforById(int id){
        return userDAO.getInforUserById(id);
    }
    public int changePass(int id,String username,String password){
        return userDAO.updateAccount(id,username,password);
    }
    public boolean validateToken(int id, String token) throws TimeoutException, NotFoundUserException, AlreadyVerifiedException {
        var user = getInforById(id);
        if (user == null) throw new NotFoundUserException("Không tìm thấy user");
        user = userDAO.getToken(user);
        if (user.isVerified()) throw new AlreadyVerifiedException("Tài khoản đã được xác minh");
        if (!user.getToken().equals(token)) return false;
        if (user.getToken().equals(token)) {
            LocalDateTime timeout = user.getTokenCreateAt();
            if (Duration.between(timeout, LocalDateTime.now()).toMinutes() >= 1) {
                throw new TimeoutException();
            }
            else
                userDAO.validate(id);
        }
        return true;
    }
    public int updateToken(int id, String token) {
        return userDAO.updateToken(id, token);
    }
    public int insert(User user) throws DuplicateInfoUserException {
        String username = user.getUsername();
        if (userDAO.getLoginInfo(username) != null)
            throw new DuplicateInfoUserException("Đã tồn tại username này trong hệ thống!");
        return userDAO.insert(user);
    }
    public int updateInfor(int id, String username, String firstname,String lastname,String email,String phone,String address, String gender, String birth){
        return userDAO.updateInfor(id,username,firstname,lastname,email,phone,address,gender,birth);
    }
    public static void main(String[] args) {
        var service = new UserService();
//        System.out.println(service.getUserByName("root"));
        System.out.println(service.updateInfor(3,"up","up","up","up","up","up","0","2023-11-10"));
//        try {
//            String em = User.hashPassword("conga");
//            System.out.println(em);
//            System.out.println(service.changePass(3,"lapusername",em));
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException(e);
//        }

    }
}
