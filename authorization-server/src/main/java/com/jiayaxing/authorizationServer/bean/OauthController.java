package com.jiayaxing.authorizationServer.bean;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

//自定义登陆认证方法。可以在里面添加认证后返回的参数并且可以修改登陆认证路径
@RestController
@RequestMapping("/oauth")
public class OauthController {
    @Autowired
    private TokenEndpoint tokenEndpoint;

    @Autowired
    ConsumerTokenServices consumerTokenServices;

    @PostMapping("/token")
    public JSONObject postAccessToken(Principal principal, @RequestParam Map<String, String> parameters){

        JSONObject jsonObject = new JSONObject();
        try {
            OAuth2AccessToken body = tokenEndpoint.postAccessToken(principal, parameters).getBody();


            jsonObject.put("access_token",body.getValue());
            jsonObject.put("refresh_token",body.getRefreshToken().getValue());
            jsonObject.put("scope",body.getScope().toString());
            jsonObject.put("token_type",body.getTokenType());
            jsonObject.put("expires_in",body.getExpiresIn());
            jsonObject.put("code",1111);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

}
