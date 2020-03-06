package com.jiayaxing.user.controller;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping(value = "userController")
public class UserController {

    @GetMapping(value = "getUserToken")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PreAuthorize("hasAnyRole('ROLE_admin')")
    public String getUserToken(Authentication authentication){
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.getCredentials();
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails)authentication.getDetails();
        String token = details.getTokenValue();
        return token;
    }

    @GetMapping(value = "jwt")
    @PreAuthorize("hasAnyRole('ROLE_admin')")
    public Object jwtParser(Authentication authentication){
        authentication.getCredentials();
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails)authentication.getDetails();
        String jwtToken = details.getTokenValue();
        Claims claims = Jwts.parser()
                .setSigningKey("dev".getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(jwtToken)
                .getBody();
        return claims;
    }

    @GetMapping(value = "get2")
    public String get2(){
        return "get2";
    }
}
