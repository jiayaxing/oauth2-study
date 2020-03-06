package com.jiayaxing.authorizationServer.service;

import com.alibaba.fastjson.JSONObject;
import com.jiayaxing.authorizationServer.dao.UserDao;
import com.jiayaxing.authorizationServer.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDao userDao;

    public UserEntity findByUsername(String username){

        UserEntity userEntity = null;
        try {
            userEntity = userDao.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userEntity;
    }

    public List<UserEntity> findByRole(String role){
        String roleAll = null;
        if(role!=null){
            roleAll="ROLE_"+role;
        }
        List<UserEntity>  users = null;
        if(roleAll!=null){
            users = userDao.findByRole(roleAll);
        }else {
            users = userDao.findAll();
        }
        return users;
    }

    @Transactional
    public JSONObject saveUserInfo(String username,String password,String role)  throws Exception {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setEncodePassword(passwordEncoder.encode(password));
        userEntity.setPassword(password);
        userEntity.setRole("ROLE_"+role);
        userDao.save(userEntity);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg", "保存成功");
        jsonObject.put("state", 1);
        return jsonObject;
    }

    public UserEntity getUserEntity(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity userEntity = userDao.findByUsername(authentication.getName());
        return userEntity;
    }
}
