package com.imooc.security.core.social.qq.api.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.security.core.social.qq.api.QQ;
import com.imooc.security.core.social.qq.api.QQUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

/**
 * QQ api
 * 不能是一个单例对象,因为每个用户的 accessToken 都是不一样的.
 * 是一个多实例对象,不能直接用@Copomnet注册.
 * @author Gatsby.Luo
 * @date 2018-11-01
 */
@Slf4j
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

    private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";

    private static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

    private ObjectMapper objectMapper = new ObjectMapper();

    //access_token 在父类AbstractOAuth2ApiBinding 已实现.

    /** oauth_consumer_key	申请QQ登录成功后，分配给应用的appId */
    private String appId;
    /** 用户的ID，与QQ号码一一对应。 */
    private String openId;

    /**
     * 构造方法,得到 open id 和app id
     * open id 在accessToken 里面. 父类已处理.
     * @param accessToken
     * @param appId
     */
    public QQImpl(String accessToken, String appId) {
        //URL_GET_OPENID--- "https://graph.qq.com/oauth2.0/me?access_token=%s"要求accessToken作为参数传进入.
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);

        this.appId = appId;
        //把accessToken替换URL_GET_OPENID中的%s;
        String url = String.format(URL_GET_OPENID, accessToken);
        //通过对指定的URL执行GET检索表示。响应(如果有的话)被转换并返回。
        //返回模版 callback( {"client_id":"YOUR_APPID","openid":"YOUR_OPENID"} );
        String result = getRestTemplate().getForObject(url, String.class);
        log.info(result);

        this.openId = StringUtils.substringBetween(result, "\"openid\":\"", "\"}");
    }

    @Override
    public QQUserInfo getQQUserInfo() {

        String url = String.format(URL_GET_USERINFO, appId, openId);
        String result = getRestTemplate().getForObject(url, String.class);
        log.info(result);
        //把字符串转为java对象

        QQUserInfo userInfo = null;
        try {
            userInfo = objectMapper.readValue(result, QQUserInfo.class);
            userInfo.setOpenId(openId);
            return userInfo;
        } catch (Exception e) {
            throw new RuntimeException("获取用户信息失败", e);
        }
    }
}
