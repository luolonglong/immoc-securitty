package com.imooc.security.core.properties;

import lombok.Data;

/**
 * 总配置类,配置QQ,微信等等.
 * @author Gatsby.Luo
 * @date 2018-11-02
 */
@Data
public class SocialProperties {

    /** url过滤链接. */
    private String filterProcessesUrl = "/auth";

    private QQProperties qq = new QQProperties();

    private WeixinProperties weixin = new WeixinProperties();
}
