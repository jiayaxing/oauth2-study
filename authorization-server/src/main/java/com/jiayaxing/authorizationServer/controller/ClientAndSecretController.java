package com.jiayaxing.authorizationServer.controller;

import com.jiayaxing.authorizationServer.service.OauthClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 增删改查这个认证服务器控制的客户端的接口
 */
@RestController
@RequestMapping(value = "clientAndSecretController")
public class ClientAndSecretController {

    @Autowired
    private OauthClientDetailsService oauthClientDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping(value = "get1")
    public Object get1(){
        return "测试1";
    }

    @GetMapping(value = "get2")
    public Object get2(){
        return "测试2";
    }

    //新增一个客户端
    @PreAuthorize("hasAnyRole('ROLE_admin')")
    @PostMapping(value = "saveOauthClientDetails")
    public String saveOauthClientDetails(@RequestParam  String clientId,@RequestParam  String clientSecret){
        String clientSecretEncoder = passwordEncoder.encode(clientSecret);
        return  oauthClientDetailsService.saveOauthClientDetails(clientId,clientSecretEncoder);
    };

    //查询所有客户端
    @GetMapping(value = "getOauthClientDetails")
    public List getOauthClientDetails(){
        return oauthClientDetailsService.getOauthClientDetails();
    }

}
