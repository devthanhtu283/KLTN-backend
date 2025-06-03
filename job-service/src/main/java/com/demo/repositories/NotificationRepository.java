package com.demo.repositories;

import com.demo.entities.Notification;
import com.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {

    @Query("SELECT n FROM Notification n WHERE n.user.id = :userId AND n.read = false")
    List<Notification> findUnreadByUser(@Param("userId") Integer userId);

    @Query("SELECT n FROM Notification n WHERE n.user.id = :userId ORDER BY n.createdAt DESC")
    List<Notification> findAllByUserOrderByCreatedAtDesc(@Param("userId") Integer userId);


}
