package com.imooc.security.app;

import com.imooc.security.app.social.openid.OpenIdAuthenticationSecurityConfig;
import com.imooc.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.imooc.security.core.properties.SecurityConstants;
import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.social.SocialAuthenticationFilterPostProcessor;
import com.imooc.security.core.validate.code.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @author Gatsby.Luo
 * @date 2018-11-09
 */
@Configuration
@EnableResourceServer
public class ImoocResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    protected AuthenticationSuccessHandler imoocAuthenticationSuccessHandler;

    @Autowired
    protected AuthenticationFailureHandler imoocAuthenticationFailureHandler;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private SpringSocialConfigurer imoocSocialSecurityConfigurer;

    @Autowired
    private OpenIdAuthenticationSecurityConfig openIdAuthenticationSecurityConfig;

    @Autowired
    private SocialAuthenticationFilterPostProcessor authorizeConfigManager;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 配置和用户名密码登录相关的配置
        http
                // formLogin()是表单登录,httpBasic()是默认的弹窗登录
                .formLogin()
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATED_URL)
                // 告诉UsernamePasswordAuthenticationFilter处理下面这个请求
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                .successHandler(imoocAuthenticationSuccessHandler)
                .failureHandler(imoocAuthenticationFailureHandler);

        http
                // 设置验证码相关的配置
                .apply(validateCodeSecurityConfig)

                // 设置短信登录相关的配置
                .and()
                .apply(smsCodeAuthenticationSecurityConfig)

                // 社交登录相关配置
                .and()
                .apply(imoocSocialSecurityConfigurer)

                // openId登录方式相关配置
                .and()
                .apply(openIdAuthenticationSecurityConfig)

                .and()
                // 关闭跨域防护伪造
                .csrf().disable();

        authorizeConfigManager.config(http.authorizeRequests());
    }
}
