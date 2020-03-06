package com.jiayaxing.authorizationServer.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class UserEntity   extends IdEntity {
    private String username;
    private String password;
    private String role;
    private String encodePassword;

    public String getEncodePassword() {
        return encodePassword;
    }

    public void setEncodePassword(String encodePassword) {
        this.encodePassword = encodePassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
