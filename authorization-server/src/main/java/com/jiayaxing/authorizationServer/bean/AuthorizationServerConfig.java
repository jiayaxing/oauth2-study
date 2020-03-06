package com.jiayaxing.authorizationServer.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.JdbcClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

//认证地址
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Resource//这里最好不用@Autowired
    private DataSource dataSource;

    @Autowired
    private SecurityUserDetailsServiceImpl securityUserDetailsService;

    @Autowired
    private TokenStore jwtTokenStore;

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired
    private TokenEnhancer jwtTokenEnhancer;



    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        /**
         * 普通 jwt 模式
         */
//         endpoints.tokenStore(jwtTokenStore)
//                .accessTokenConverter(jwtAccessTokenConverter)
//                .userDetailsService(kiteUserDetailsService)
//                /**
//                 * 支持 password 模式
//                 */
//                .authenticationManager(authenticationManager);

        /**
         * jwt 增强模式
         */
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> enhancerList = new ArrayList<>();
        enhancerList.add(jwtTokenEnhancer);
        enhancerList.add(jwtAccessTokenConverter);
        enhancerChain.setTokenEnhancers(enhancerList);
        endpoints.tokenStore(jwtTokenStore)
                .userDetailsService(securityUserDetailsService)
                /**
                 * 支持 password 模式
                 */
                .authenticationManager(authenticationManager)
                .tokenEnhancer(enhancerChain)
                .accessTokenConverter(jwtAccessTokenConverter);

        /**
         * redis token 方式
         */
//        endpoints.authenticationManager(authenticationManager)
//                .tokenStore(redisTokenStore)
//                .userDetailsService(kiteUserDetailsService);
    }

    //这个配置使的其他项目在获取解析本认证服务器颁发的jwt的密钥时，必须要经过身份认证才能获取
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients();
        security.tokenKeyAccess("isAuthenticated()");//其他服务器访问tokenkey的时候需要身份认证
        security.checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //内存模式
//        clients.inMemory().withClient("user-client")
//                .secret(passwordEncoder.encode("user-secret-8888"))//这个地方在springboot1.x版本中不需要加密的，但是2.x版本需要加密
//                .authorizedGrantTypes("authorization-code","password","refresh_token")//这样写的话，我们获取的token里不会有refresh_token
//                .scopes("all")
//                .accessTokenValiditySeconds(36000)
//                .and()
//                .withClient("client-order")
//                .secret(passwordEncoder.encode("client-order-8888"))
//                .authorizedGrantTypes("authorization-code","password","refresh_token")
//                .scopes("all")
//                .accessTokenValiditySeconds(36000)
//                .and()
//                .withClient("web")
//                .secret(passwordEncoder.encode("web-8888"))
//                .authorizedGrantTypes("authorization-code","password","refresh_token")
//                .scopes("all")
//                .accessTokenValiditySeconds(36000);

        //数据库模式
        JdbcClientDetailsServiceBuilder jcsb = clients.jdbc(dataSource);
        jcsb.passwordEncoder(passwordEncoder);

    }


}
