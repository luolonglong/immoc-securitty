package com.imooc.security.core.validate.code.sms;

/**
 * @author Gatsby.Luo
 * @date 2018-10-30
 */
public interface SmsCodeSender {

    /** 发送验证码 */
    void send(String mobile, String code);
}
