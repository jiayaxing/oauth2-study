package com.jiayaxing.authorizationServer.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        AuthenticationManager manager = super.authenticationManagerBean();
        return manager;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.formLogin()
//                .and()
//                .authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .logout().permitAll()
//                .and()
//                .csrf().disable();
//    }

    /**
     * 允许匿名访问所有接口 主要是 oauth 接口
     * @param http
     * @throws Exception
     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        //http.authorizeRequests().antMatchers("/**").permitAll();
//        http.authorizeRequests().antMatchers(PermitAllUrl.permitAllUrl()).permitAll().antMatchers("/clientAndSecretController/get2").permitAll().anyRequest().authenticated().and()
//                //关闭跨站请求防护
//                .csrf().disable();
//    }


}
