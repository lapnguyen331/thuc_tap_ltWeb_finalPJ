package com.project.db;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.sql.SQLException;

public class JDBIConnector2 {
    private static Jdbi jdbi;

    private static void makeConnect() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL("jdbc:mysql://" + DBProperties2.getDbHost() + ":" + DBProperties2.getDbPort() + "/"
                + DBProperties2.getDbName());
        dataSource.setUser(DBProperties2.getUsername());
        dataSource.setPassword(DBProperties2.getPassword());
        try {
            dataSource.setUseCompression(true);
            dataSource.setAutoReconnect(true);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
        jdbi = Jdbi.create(dataSource);
        jdbi.installPlugin(new SqlObjectPlugin());
    }


    private JDBIConnector2() {
    }

    public static Jdbi get() {
        if(jdbi==null) makeConnect();
        return jdbi;
    }

    public static void main(String[] args) {

    }
}
