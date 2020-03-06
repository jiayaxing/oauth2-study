package com.jiayaxing.authorizationServer.dao;

import com.jiayaxing.authorizationServer.entity.OauthClientDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OauthClientDetailsDao  extends JpaRepository<OauthClientDetailsEntity, String> {

}
