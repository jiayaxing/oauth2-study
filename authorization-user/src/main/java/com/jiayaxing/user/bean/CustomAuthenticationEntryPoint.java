package com.jiayaxing.user.bean;

import com.alibaba.fastjson.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationEntryPoint  implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        JSONObject jsonObject = new JSONObject();
        Throwable cause = e.getCause();
        if(cause instanceof InvalidTokenException) {
            jsonObject.put("code",0);
            jsonObject.put("msg", "无效的token");
        }else{
            jsonObject.put("code", 0);
            jsonObject.put("msg", "身份验证失败");
        }
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setContentType("text/javascript;charset=utf-8");
        httpServletResponse.getWriter().print(jsonObject.toString());
    }
}
