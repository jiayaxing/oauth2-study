package com.jiayaxing.authorizationServer.bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * SecurityConfig
 *
 * @author fengzheng
 * @date 2019/10/11
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers(PermitAllUrl.permitAllUrl()).permitAll().antMatchers("/clientAndSecretController/get2").permitAll().anyRequest().authenticated().and()
                //关闭跨站请求防护
                .csrf().disable();
    }



    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.authenticationEntryPoint(new CustomAuthenticationEntryPoint());
        resources.accessDeniedHandler(new CustomAccessDeineHandler());
    }
}
