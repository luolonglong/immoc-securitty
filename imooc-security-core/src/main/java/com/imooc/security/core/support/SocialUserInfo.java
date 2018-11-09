package com.imooc.security.core.support;

import lombok.Data;

/**
 * @author Gatsby.Luo
 * @date 2018-11-05
 */
@Data
public class SocialUserInfo {

    private String providerId;

    private String providerUserId;
    /** 昵称 */
    private String nickname;
    /** 头像 */
    private String headImg;
}
