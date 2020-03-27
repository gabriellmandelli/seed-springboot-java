package com.greentower.seedApi.model.repository;

import com.greentower.seedApi.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    @Query("SELECT u FROM auth_user u WHERE u.userName = :username and u.password = :password")
    User findByLoginPassword(@Param("username") String login, @Param("password") String password);
}
