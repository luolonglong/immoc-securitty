package com.imooc.security.core.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import static com.imooc.security.core.properties.SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM;
import static com.imooc.security.core.properties.SecurityConstants.DEFAULT_UNAUTHENTICATED_URL;

/**
 * @author Gatsby.Luo
 * @date 2018-10-31
 */
public class AbstractChannelSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    protected AuthenticationSuccessHandler imoocAuthenticationSuccessHandler;

    @Autowired
    protected AuthenticationFailureHandler imoocAuthenticationFailureHandler;

    protected void applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage(DEFAULT_UNAUTHENTICATED_URL)
                .loginProcessingUrl(DEFAULT_LOGIN_PROCESSING_URL_FORM)
                .successHandler(imoocAuthenticationSuccessHandler)
                .failureHandler(imoocAuthenticationFailureHandler);
    }

}
