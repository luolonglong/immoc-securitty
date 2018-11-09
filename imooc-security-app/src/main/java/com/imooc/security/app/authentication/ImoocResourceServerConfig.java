package com.imooc.security.app.authentication;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @author Gatsby.Luo
 * @date 2018-11-07
 */

@Configuration
@EnableResourceServer
public class ImoocResourceServerConfig {
     //@EnableResourceServer 让依赖app 的demo成为资源服务器.
}
