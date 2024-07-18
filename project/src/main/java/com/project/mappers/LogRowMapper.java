package com.project.mappers;

import com.project.models.Log;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LogRowMapper extends ARowMapper<Log> {
    public LogRowMapper(String alias) {
        super(alias);
    }

    public LogRowMapper() {
    }

    @Override
    public Log map(ResultSet rs, StatementContext ctx) throws SQLException {
        Log log = null;
        int id = getValueAt(rs, this.alias+"id", ctx, int.class);
        int level = getValueAt(rs, this.alias+"level", ctx, int.class);
        String ip = getValueAt(rs, this.alias+"ip", ctx, String.class);
        String national = getValueAt(rs, this.alias+"national", ctx, String.class);
        String previous = getValueAt(rs, this.alias+"previous", ctx, String.class);
        String current = getValueAt(rs, this.alias+"current", ctx, String.class);
        log = new Log(id, level, ip, national, previous, current);
        return log;
    }
}
