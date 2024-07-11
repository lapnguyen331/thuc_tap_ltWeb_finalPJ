package com.project.service_rework;

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
        if (isPassed) {
            throw new RuntimeException("Cannot modify passed Handle at this service");
        }
        handle.begin();
    }
    public void commit() {
        if (isPassed) {
            throw new RuntimeException("Cannot modify passed Handle at this service");
        }
        handle.commit();
    }
    public void rollback() {
        if (isPassed) {
            throw new RuntimeException("Cannot modify passed Handle at this service");
        }
        handle.rollback();
    }
    public void close() {
        if (isPassed) {
            throw new RuntimeException("Cannot modify passed Handle at this service");
        }
        handle.close();
    }
    public Handle getHandle() {
        return handle;
    }
}
