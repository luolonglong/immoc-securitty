package com.imooc.security.core.properties;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.autoconfigure.social.SocialProperties;
/**
 * @author Gatsby.Luo
 * @date 2018-11-05
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WeixinProperties extends SocialProperties {
    /**
     * 第三方id，用来决定发起第三方登录的url，默认是 weixin。
     */
    private String providerId = "weixin";

}
