package com.project.dao_rework;
import com.project.models_rework.Chat;
import com.project.models_rework.User;
import org.jdbi.v3.sqlobject.SqlObject;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.config.RegisterBeanMappers;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.Define;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RegisterBeanMappers({
        @RegisterBeanMapper(Chat.class),
        @RegisterBeanMapper(LocalDateTime.class)
})
public interface ChatDAO extends SqlObject {
    @SqlQuery("""
            SELECT ch.*
            FROM chat_history ch
            JOIN (
                SELECT
                    LEAST(receiverId, senderId) as user1,
                    GREATEST(receiverId, senderId) as user2,
                    MAX(createAt) as latest_time
                FROM chat_history
                WHERE senderId = :userId OR receiverId = :userId
                GROUP BY user1, user2
            ) latest ON (LEAST(ch.receiverId, ch.senderId) = latest.user1)
                      AND (GREATEST(ch.receiverId, ch.senderId) = latest.user2)
                      AND ch.createAt = latest.latest_time
            ORDER BY ch.createAt DESC;
            """)
    List<Chat> getLatestChatGroupByUserIdAndReceiverId(@Bind("userId") Integer userId);

    @SqlQuery("""
            SELECT *
            FROM chat_history
            WHERE 
                (receiverId = :id1 AND senderId = :id2) 
            OR
                (senderId = :id1 AND receiverId = :id2)
            ORDER BY createAt;
            """)
    List<Chat> getAllChatBetweenUserIds(@Bind("id1") Integer userId1, @Bind("id2") Integer userId2);

    @SqlUpdate("""
            INSERT INTO chat_history 
                (senderId, receiverId, message, createAt, isNew)
            VALUES 
                (:c.senderId, :c.receiverId, :c.message, :c.createAt, :c.isNew)
            """)
    Integer insertNewChat(@BindBean("c") Chat chat);

    @SqlUpdate("""
            UPDATE chat_history
            SET 
                isNew = 0
            WHERE
                senderId = :senderId AND receiverId = :receiverId
                AND createAt <= :time 
            """)
    Integer markReadMessage(@Bind("senderId") Integer senderId,
                            @Bind("receiverId") Integer receiverId,
                            @Bind("time") LocalDateTime time);
}
