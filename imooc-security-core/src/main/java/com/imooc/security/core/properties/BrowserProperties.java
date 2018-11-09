package com.imooc.security.core.properties;

import lombok.Data;

/**
 * 配置项默认值.
 * 在application.yml配置配置项的定制应用值.
 * @author Gatsby.Luo
 * @date 2018-10-25
 */
@Data
public class BrowserProperties {

    /** 设置默认值.如果用户没有配置登录页,则跳转至标准登录页. */
    private String signUpUrl = "/imooc-signUp.html";

    private String loginPage = SecurityConstants.DEFAULT_LOGIN_PAGE_URL;

    private String signOutUrl = "/imooc-signOut.html";

    /** 设置默认值.如果用户没有配置登录返回类型,则默认Json. */
    private LoginResponseType loginType = LoginResponseType.JSON;

    /** 记住我的时间 */
    private int rememberMeSeconds = 3600;

    private SessionProperties session = new SessionProperties();

}
