package com.imooc.security.core.validate.code.sms;

import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.properties.SmsCodeProperties;
import com.imooc.security.core.validate.code.ValidateCode;
import com.imooc.security.core.validate.code.ValidateCodeGenerator;
import lombok.Data;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author Gatsby.Luo
 * @date 2018-10-29
 */
@Data
@Component("smsValidateCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator {
    private final SecurityProperties securityProperties;

    @Autowired
    public SmsCodeGenerator(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    @Override
    public ValidateCode generate(ServletWebRequest request) {

        SmsCodeProperties sms = securityProperties.getCode().getSms();
        String code = RandomStringUtils.randomNumeric(sms.getLength());
        return new ValidateCode(code, sms.getExpireIn());
    }

}
