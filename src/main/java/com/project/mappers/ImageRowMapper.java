package com.project.mappers;

import com.project.db.JDBIConnector;
import com.project.models.Image;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.collector.CollectorFactory;
import org.jdbi.v3.core.config.ConfigRegistry;
import org.jdbi.v3.core.mapper.*;
import org.jdbi.v3.core.mapper.reflect.BeanMapper;
import org.jdbi.v3.core.mapper.reflect.ConstructorMapper;
import org.jdbi.v3.core.result.RowView;
import org.jdbi.v3.core.result.internal.RowViewImpl;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class ImageRowMapper extends ARowMapper<Image> {
    public ImageRowMapper(String alias) {
        super(alias);
    }

    public ImageRowMapper() {
    }

    @Override
    public Image map(ResultSet rs, StatementContext ctx) {
        Image img;
        int id = getValueAt(rs, this.alias+"id", ctx, int.class);
        String path = getValueAt(rs, this.alias+"path", ctx, String.class);
        String uuid = getValueAt(rs, this.alias+"uuid", ctx, String.class);
        LocalDateTime createAt = getValueAt(rs, this.alias+"createAt", ctx, LocalDateTime.class);
        LocalDateTime updateAt = getValueAt(rs, this.alias+"updateAt", ctx, LocalDateTime.class);
        img = new Image(id, path, uuid, createAt, updateAt);
        return img;
    }

}
