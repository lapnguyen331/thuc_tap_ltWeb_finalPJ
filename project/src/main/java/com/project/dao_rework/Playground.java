package com.project.dao_rework;

import com.project.db.JDBIConnector;
import org.jdbi.v3.core.Handle;

import java.util.Arrays;

public class Playground {
    public static void main(String[] args) {
        Handle handle = JDBIConnector.get().open();
        ImageDAO dao = handle.attach(ImageDAO.class);
        System.out.println(dao.getImagesByIds(Arrays.asList(1047, 1048, 1049)));
    }
}
