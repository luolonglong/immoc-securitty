package com.imooc.security.core.validate.code.sms;

/**
 * @author Gatsby.Luo
 * @date 2018-10-30
 */
public class DefaultSmsCodeSender implements SmsCodeSender {
    @Override
    public void send(String mobile, String code) {
        System.out.println("向" + mobile + "发送了一条验证码" + code);
    }
}
