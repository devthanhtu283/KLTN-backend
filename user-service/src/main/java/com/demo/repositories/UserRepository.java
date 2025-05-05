package com.demo.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.demo.entities.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
//    @Query("from User where username = :username")
//    public User findByUsername(@Param("username") String username);

    Optional<User> findByUsername(String username);


    @Query("from User where email = :email")
    public User findByEmail(@Param("email") String email);
}
