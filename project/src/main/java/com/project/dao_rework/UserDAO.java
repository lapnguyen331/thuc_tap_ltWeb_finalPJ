package com.project.dao_rework;
import com.project.models_rework.Image;
import com.project.models_rework.User;
import org.jdbi.v3.sqlobject.SqlObject;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.config.RegisterBeanMappers;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.Define;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.UseRowReducer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RegisterBeanMappers({
        @RegisterBeanMapper(User.class),
        @RegisterBeanMapper(LocalDate.class),
        @RegisterBeanMapper(LocalDateTime.class)
})
public interface UserDAO extends SqlObject {
    @SqlQuery("SELECT <columns> FROM users <where_clause>")
    List<User> getUser(@Define(value = "columns") String columns,
                       @Define(value = "where_clause") String whereClause);

    @SqlQuery("SELECT * FROM users")
    List<User> getAllUsers();

    @SqlQuery("SELECT * FROM users where id = :id")
    List<User> getUserById(@Bind("id") Integer id);

    @SqlQuery("SELECT * FROM users where username = :username")
    List<User> getUserByUsername(@Bind("username") String username);

//    @RegisterBeanMapper(value = User.class, prefix = "u")
//    @RegisterBeanMapper(value = Image.class, prefix = "i")
//    @UseRowReducer(UserAvatarReducer.class)
//    @SqlQuery("""
//        SELECT u.id u_id, u.username u_username, u.levelAccess u_levelAccess,
//            u.firstName u_firstName, u.lastName u_lastName,
//            u.gender u_gender, u.address u_address,
//            u.phone u_phone, u.birth u_birth,
//            u.status u_status, u.email u_email,
//            u.verified u_verified, u.createAt u_createAt,
//            u.updateAt u_updateAt, u.token u_token,
//            u.tokenCreateAt u_tokenCreateAt
//        i.id i_id, i.path i_path
//        FROM users u LEFT JOIN images i
//        ON u.avatar = i.id
//        WHERE u.id = :id
//    """)
//    List<User> getUserWithImageById(@Bind("id") Integer id);
//
//    class UserAvatarReducer implements LinkedHashMapRowReducer<Integer, User> {
//        @Override
//        public void accumulate(Map<Integer, User> map, RowView rowView) {
//            User user = map.computeIfAbsent(rowView.getColumn("u_id", Integer.class),
//                    id -> rowView.getRow(User.class));
//
//            if (rowView.getColumn("i_id", Integer.class) != null) {
//                user.setAvatarObj(rowView.getRow(Image.class));
//            }
//        }
//    }
}
