package com.imooc.security.core.properties;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * qq配置
 * @author Gatsby.Luo
 * @date 2018-11-02
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QQProperties extends SocialProperties {

    private String providerId = "qq";

}
