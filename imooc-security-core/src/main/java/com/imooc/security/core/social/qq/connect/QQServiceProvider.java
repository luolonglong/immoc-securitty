package com.imooc.security.core.social.qq.connect;

import com.imooc.security.core.social.qq.api.impl.QQImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

/**
 * @author Gatsby.Luo
 * @date 2018-11-01
 */

public class QQServiceProvider extends AbstractOAuth2ServiceProvider {

    private String appId;

    /** Step1：获取Authorization Code */
    private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";

    /** Step2：通过Authorization Code获取Access Token */
    private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";

    public QQServiceProvider(String appId, String appSecret) {
        // 用户名 ,密码,同意授权访问的url,申请令牌访问的url.
        super(new QQAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
        this.appId = appId;
    }

    /**
     * 多例模式.QQImpl
     * @param accessToken
     * @return
     */
    @Override
    public Object getApi(String accessToken) {
        return new QQImpl(accessToken, appId);
    }
}
