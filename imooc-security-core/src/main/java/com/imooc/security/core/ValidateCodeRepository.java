package com.imooc.security.core;

import com.imooc.security.core.validate.code.ValidateCode;
import com.imooc.security.core.validate.code.ValidateCodeType;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author Gatsby.Luo
 * @date 2018-11-08
 */
public interface ValidateCodeRepository {

    void save(ServletWebRequest request, ValidateCode code, ValidateCodeType validateCodeType);

    ValidateCode get(ServletWebRequest request, ValidateCodeType validateCodeType);

    void remove(ServletWebRequest request, ValidateCodeType validateCodeType);
}
