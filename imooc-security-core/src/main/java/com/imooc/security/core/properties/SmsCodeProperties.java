package com.imooc.security.core.properties;

import lombok.Data;

/**
 * @author Gatsby.Luo
 * @date 2018-10-29
 */
@Data
public class SmsCodeProperties {
    /** 验证码长度 */
    private int length = 6;
    /** 过期时间 */
    private int expireIn = 60*5;
    /**  */
    private String url;
}
