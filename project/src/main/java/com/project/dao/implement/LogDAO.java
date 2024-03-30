package com.project.dao.implement;

import com.project.dao.AbstractDAO;
import com.project.dao.ILogDAO;
import com.project.models.Log;
import org.jdbi.v3.core.Handle;

public class LogDAO extends AbstractDAO<Log> implements ILogDAO {

    public LogDAO(Handle handle) {
        super(handle);
    }

    @Override
    public void insert(Log log) {
//        final String INSERT = "INSERT INTO <table> (<columns>) VALUES (<values>)";
//        return insert(INSERT, update -> {
//            update.define("table", "logs")
//        });
    }
}
