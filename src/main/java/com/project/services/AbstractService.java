package com.project.services;

import com.project.db.JDBIConnector;
import org.jdbi.v3.core.Handle;

public class AbstractService {
    protected final Handle handle;
    protected final boolean isPassed;

    public AbstractService() {
        this.handle = JDBIConnector.get().open();
        this.isPassed = false;
    }

    public AbstractService(Handle handle) {
        this.handle = handle;
        this.isPassed = true;
    }

    public void begin() {
        if (!isPassed) handle.begin();
    }
    public void commit() {
        if (!isPassed) handle.commit();
    }
    public void rollback() {
        if (!isPassed) handle.rollback();
    }
    public void close() {
        if (!isPassed) handle.close();
    }
    public Handle getHandle() {
        return handle;
    }
}
