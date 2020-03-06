package com.jiayaxing.authorizationServer.dao;

import com.jiayaxing.authorizationServer.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserDao extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);

    List<UserEntity> findByRole(String role);

    @Query(value = "SELECT username FROM user WHERE id=?1", nativeQuery = true)
    String findByID(long userId);

}
