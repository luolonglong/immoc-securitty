package com.imooc.security.browser;

import com.imooc.security.browser.logout.ImoocLogoutSuccessHandler;
import com.imooc.security.browser.session.ImoocExpiredSessionStrategy;
import com.imooc.security.core.authentication.AbstractChannelSecurityConfig;
import com.imooc.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.imooc.security.core.properties.BrowserProperties;
import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.validate.code.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

import static com.imooc.security.core.properties.SecurityConstants.*;

/**
 * @author Gatsby.Luo
 * @date 2018-10-25
 */
@Configuration
public class BrowserSecurityConfig extends AbstractChannelSecurityConfig {

    @Autowired
    private SecurityProperties securityProperties;

    /** 这里DateSource就会读取application.properties中配置的数据源信息 */
    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    /** 导入配置类 */
    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    /** SpringSocialConfigurer 配置类. 在socialConfig中注入了Bean */
    @Autowired
    private SpringSocialConfigurer imoocSocialSecurityConfigurer;

    /** 短信验证码配置 */
    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private LogoutSuccessHandler imoocLogoutSuccessHandler;

    private BrowserProperties browser = securityProperties.getBrowser();

    /** remember me相关配置 */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        // tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;

    }

    @Bean
    @ConditionalOnMissingBean(LogoutSuccessHandler.class)
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new ImoocLogoutSuccessHandler(browser.getSignOutUrl());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //直接调用父类方法  配置和用户名密码登录相关的配置
        applyPasswordAuthenticationConfig(http);

        http.apply(validateCodeSecurityConfig)

                .and()
                .apply(smsCodeAuthenticationSecurityConfig)

                // 社交登录相关配置
                .and()
                .apply(imoocSocialSecurityConfigurer)

                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(browser.getRememberMeSeconds())
                .userDetailsService(userDetailsService)

                // Session相关配置
                .and()
                .sessionManagement()
                .invalidSessionUrl(DEFAULT_SESSION_INVALID_URL)
                .maximumSessions(browser.getSession().getMaximumSessions())
                .maxSessionsPreventsLogin(browser.getSession().isMaxSessionsPreventsLogin())
                .expiredSessionStrategy(new ImoocExpiredSessionStrategy())
                .and()

                .and()
                .logout()
                .logoutUrl("/signOut")
                .logoutSuccessHandler(imoocLogoutSuccessHandler)
                .deleteCookies("JSESSIONID")

                .and()
                .authorizeRequests()
                //过滤自定义的loginPage,避免localhost 将您重定向的次数过多错误。
                .antMatchers(DEFAULT_UNAUTHENTICATED_URL,
                        DEFAULT_VALIDATE_CODE_URL_PREFIX + "/**",
                        DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                        browser.getSignUpUrl(),
                        browser.getLoginPage(),
                        browser.getSignOutUrl(),
                        "/user/regist",
                        DEFAULT_SESSION_INVALID_URL + ".json",
                        DEFAULT_SESSION_INVALID_URL + ".html"
                )
                .permitAll()
                .anyRequest()
                .authenticated()

                .and()
                .csrf().disable();
    }
}
