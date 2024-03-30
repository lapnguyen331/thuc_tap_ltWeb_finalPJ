package com.project.dao.implement;

import com.project.dao.AbstractDAO;
import com.project.dao.IContactDAO;
import com.project.dao.IRatingDAO;
import com.project.mappers.*;
import com.project.models.Contact;
import com.project.models.Product;
import com.project.models.Rating;
import org.jdbi.v3.core.Handle;

import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class ContactDAO extends AbstractDAO<Contact> implements IContactDAO {
    public ContactDAO(Handle handle) {
        super(handle);
    }

    @Override
    public List<Contact> getAll() {
        final String SELECT = "SELECT * FROM <table1> u" ;
        return query(SELECT, Contact.class, (query -> {
            query.define("table1","contact");
        }), new ContactRowMapper("u"));
    }


    @Override
    public List<Contact> getUncheckNoti() {
        final String SELECT = "SELECT * FROM <table1> u where u.status =0" ;
        return query(SELECT, Contact.class, (query -> {
            query.define("table1","contact");
        }), new ContactRowMapper("u"));
    }

    @Override
    public int insert(Contact r) {
        var keys = Arrays.asList(
                "firstName",
                "lastName",
                "phone",
                "email",
                "content",
                "status",
                "createAt",
                "updateAt"
        );
        var values = Arrays.asList(
                r.getFirstName(),
                r.getLastName(),
                r.getPhone(),
                r.getEmail(),
                r.getContent(),
                r.getStatus(),
                r.getCreateAt() == null ? LocalDateTime.now() : r.getCreateAt(),
                r.getUpdateAt() == null ? LocalDateTime.now() : r.getUpdateAt()
        );
        final String INSERT = "INSERT INTO <table> (<columns>) VALUES (<values>)";
        return insertAndReturnGeneratedKeys(INSERT, "id", (update ->  {
            update.define("table", "contact")
                    .defineList("columns", keys)
                    .bindList("values", values);
        })).mapTo(int.class).findOne().orElse(-1);
    }
}
