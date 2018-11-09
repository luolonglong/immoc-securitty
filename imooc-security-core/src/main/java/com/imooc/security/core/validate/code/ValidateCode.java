package com.imooc.security.core.validate.code;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Gatsby.Luo
 * @date 2018-10-26
 */
@Data
@AllArgsConstructor
public class ValidateCode implements Serializable {

    private static final long serialVersionUID = -4771608111819823038L;

    private String code;

    /** 过期时间 */
    private LocalDateTime expireTime;

    public ValidateCode(String code, int expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expireTime);
    }

}
