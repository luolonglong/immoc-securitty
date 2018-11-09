package com.imooc.security.core.social.weixin.api;

/**
 * @author Gatsby.Luo
 * @date 2018-11-05
 */
public interface Weixin {

    WeixinUserInfo getUserInfo(String openId);
}
