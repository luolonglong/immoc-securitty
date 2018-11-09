package com.imooc.security.core.social;

import org.springframework.social.security.SocialAuthenticationFilter;

/**
 * 社交登录过滤器的后处理器
 * @author Gatsby.Luo
 * @date 2018-11-09
 */
public interface SocialAuthenticationFilterPostProcessor {

    void process(SocialAuthenticationFilter socialAuthenticationFilter);
}
