package com.project.service_rework;


import com.project.dao_rework.LogDAO;
import com.project.models_rework.log.Log;

import java.util.Arrays;
import java.util.List;

public class LogService extends AbstractService {
    LogDAO logDao = getHandle().attach(LogDAO.class);

    //xuất bản log, ở đây là insert db
    public void publish(Log log) {
        logDao.insert(log);
        System.out.println("đã insert log");
    }
    public List<Log> getAll(){
        return logDao.getCurrLog();
    }
    //xóa theo id : mặc dịnh 0 sẽ là không xóa dc, 1 xóa được
    public int deleteById(Integer id ){
        return logDao.deleteById(id);
    }

    public static void main(String[] args) {
        LogService l = new LogService();
    }


}
