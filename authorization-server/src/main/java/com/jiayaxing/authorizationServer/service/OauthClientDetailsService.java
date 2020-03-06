package com.jiayaxing.authorizationServer.service;

import com.jiayaxing.authorizationServer.dao.OauthClientDetailsDao;
import com.jiayaxing.authorizationServer.entity.OauthClientDetailsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class OauthClientDetailsService {

    @Autowired
    private OauthClientDetailsDao oauthClientDetailsDao;

    @Transactional
    public String saveOauthClientDetails(String clientId,String clientSecret){
        OauthClientDetailsEntity oauthClientDetailsEntity = new OauthClientDetailsEntity();
        oauthClientDetailsEntity.setClientId(clientId);
        oauthClientDetailsEntity.setClientSecret(clientSecret);
        oauthClientDetailsEntity.setScope("all");
        oauthClientDetailsEntity.setAuthorizedGrantTypes("authorization_code,refresh_token,password");
        oauthClientDetailsEntity.setAccessTokenValidity(3600);
        oauthClientDetailsEntity.setRefreshTokenValidity(36000);
        oauthClientDetailsEntity.setAutoapprove("1");

        oauthClientDetailsDao.save(oauthClientDetailsEntity);

        return "新增客户端成功";
    }

    public List getOauthClientDetails(){
        List<OauthClientDetailsEntity> all = oauthClientDetailsDao.findAll();
        return all;
    }

}
