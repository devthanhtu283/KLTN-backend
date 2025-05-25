package com.demo.repositories;

import com.demo.entities.Chat;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepository extends CrudRepository<Chat, Integer> {
    @Query(value = "SELECT DISTINCT c.receiver_id FROM chat c WHERE c.sender_id = :userId " +
            "UNION " +
            "SELECT DISTINCT c.sender_id FROM chat c WHERE c.receiver_id = :userId",
            nativeQuery = true)
    List<Integer> findAllReceiverIdsByUserId(@Param("userId") Integer userId);

    @Query("SELECT c FROM Chat c " +
            "WHERE (c.userBySenderId.id = :senderId AND c.userByReceiverId.id = :receiverId) " +
            "OR (c.userBySenderId.id = :receiverId AND c.userByReceiverId.id = :senderId) " +
            "ORDER BY c.time ASC")
    List<Chat> findMessagesBetweenUsers(@Param("senderId") Integer senderId, @Param("receiverId") Integer receiverId);

    @Query("SELECT c FROM Chat c WHERE c.id IN (" +
            "SELECT MAX(c2.id) FROM Chat c2 WHERE c2.userBySenderId.id = :userId OR c2.userByReceiverId.id = :userId " +
            "GROUP BY CASE WHEN c2.userBySenderId.id = :userId THEN c2.userByReceiverId.id ELSE c2.userBySenderId.id END) " +
            "ORDER BY c.time DESC")
    List<Chat> findRecentMessagesByUserId(@Param("userId") Integer userId);
}
