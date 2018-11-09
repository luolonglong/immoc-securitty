package com.imooc.security.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 配置类,绑定配置,读取imooc.security开头的配置项
 * @author Gatsby.Luo
 * @date 2018-10-25
 */
@ConfigurationProperties(prefix = "imooc.security")
@Data
public class SecurityProperties {

    private BrowserProperties browser = new BrowserProperties();

    private ValidateCodeProperties code = new ValidateCodeProperties();

    /** 注入自己的socialProperties,配置里面的QQ/微信等等实现 */
    private SocialProperties social = new SocialProperties();


}
