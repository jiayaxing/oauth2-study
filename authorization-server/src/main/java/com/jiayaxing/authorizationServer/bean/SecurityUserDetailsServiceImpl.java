package com.jiayaxing.authorizationServer.bean;

import com.jiayaxing.authorizationServer.entity.UserEntity;
import com.jiayaxing.authorizationServer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class SecurityUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         UserEntity userEntity = userService.findByUsername(username);
        String password = userEntity.getEncodePassword();//这个加密是在注册时加密的，这里实际只用查询数据库中已经加密好的密码即可
        String role = userEntity.getRole();
        boolean accountNonExpired = true; // 该账户是否过期，这里是没有过期
        boolean enabled = true; // 账户是否被删除，这里是没有被删除
        boolean credentialsNonExpired = true;// 账户认证是否过期，这里是没有过期
        boolean accountNonLocked = true; // 查询该账户是否被锁定，这里是没有被锁定

        Collection<GrantedAuthority> grantedAuthorities =  AuthorityUtils.commaSeparatedStringToAuthorityList(role);

        return new User(username, password,
                enabled, accountNonExpired,
                credentialsNonExpired, accountNonLocked,grantedAuthorities);//这个方法中去对比用户登录的密码和该账号查询到的密码是否一致

    }
}
