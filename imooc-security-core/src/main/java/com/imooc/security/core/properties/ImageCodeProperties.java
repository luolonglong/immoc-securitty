package com.imooc.security.core.properties;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Gatsby.Luo
 * @date 2018-10-29
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class ImageCodeProperties extends SmsCodeProperties {

    private int width = 67;
    private int height = 23;

    public ImageCodeProperties() {
        // TODO: 2018/10/29 测试
        super.setLength(4);
    }

}
