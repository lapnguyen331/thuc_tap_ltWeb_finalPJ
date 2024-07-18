package com.project.services;

import com.project.dao.ILogDAO;
import com.project.dao.implement.FactoryDAO;
import com.project.dao.implement.LogDAO;
import org.jdbi.v3.core.Handle;

public class LogService extends AbstractService {
    private final ILogDAO logDAO;

    public LogService() {
        this.logDAO = FactoryDAO.getDAO(handle, FactoryDAO.DAO_LOG);
    }

    public LogService(Handle handle) {
        super(handle);
        this.logDAO = FactoryDAO.getDAO(handle, FactoryDAO.DAO_LOG);
    }

    public void insert() {

    }
}
