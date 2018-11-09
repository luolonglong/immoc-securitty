package com.imooc.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 验证码生成器,包括图形验证码和短信验证码
 * @author Gatsby.Luo
 * @date 2018-10-29
 */
public interface ValidateCodeGenerator {

    /** 验证码生成函数 */
    ValidateCode generate(ServletWebRequest request);
}
