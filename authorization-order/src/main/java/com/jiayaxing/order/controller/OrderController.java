package com.jiayaxing.order.controller;

import com.jiayaxing.order.feignClient.UserFeignClient;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping(value = "orderController")
public class OrderController {

    @Resource
    private UserFeignClient userFeignClient;

    @GetMapping(value = "get")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PreAuthorize("hasAnyRole('ROLE_admin')")
    public Object get(Authentication authentication){
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        authentication.getCredentials();
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails)authentication.getDetails();
        String jwtToken = details.getTokenValue();
        Claims claims = Jwts.parser()
                .setSigningKey("dev".getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(jwtToken)
                .getBody();
        return claims;
        //return "给你";
    }

    @GetMapping(value = "get1")
    public Object get1(){
        return "给你";
    }

    //order调用user服务的需要授权的接口必须携带传过来的tonken，要不然user接口回拒绝访问
    @GetMapping(value = "getUserToken")
    public String getUserToken(Authentication authentication) {
        authentication.getCredentials();
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails)authentication.getDetails();
        String jwtToken = details.getTokenValue();
        return userFeignClient.getUserToken("Bearer "+jwtToken);
    }

    @GetMapping(value = "getUserGet2")
    public String getUserGet2() {
        return userFeignClient.get2();
    }
}
