package com.imooc.security.app.authentication;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * @author Gatsby.Luo
 * @date 2018-11-07
 */
@Configuration
@EnableAuthorizationServer
public class ImoocAuthenticationServerConfig {
    //@EnableAuthorizationServer 让依赖app 的demo成为认证服务器.
}
