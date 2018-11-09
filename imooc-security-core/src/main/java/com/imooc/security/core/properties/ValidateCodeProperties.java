package com.imooc.security.core.properties;

import lombok.Data;

/**
 * @author Gatsby.Luo
 * @date 2018-10-29
 */
@Data
public class ValidateCodeProperties {

    private ImageCodeProperties image = new ImageCodeProperties();

    private SmsCodeProperties sms = new SmsCodeProperties();
}
