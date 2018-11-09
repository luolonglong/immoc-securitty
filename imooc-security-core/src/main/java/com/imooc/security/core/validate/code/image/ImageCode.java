package com.imooc.security.core.validate.code.image;

import com.imooc.security.core.validate.code.ValidateCode;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * session存储在redis中,在生成imageCode时,将其放入session中.
 * 放在redis中的东西都是需要序列化的.
 * @author Gatsby.Luo
 * @date 2018-10-29
 */

public class ImageCode extends ValidateCode {

    /** BufferedImage 是JDK自带的,没有实现序列化接口. */
    private BufferedImage image;

    /** 调用父类的两个构造方法 */
    public ImageCode(BufferedImage image, String code, int expireIn) {
        super(code, expireIn);
        this.image = image;
    }

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        super(code, expireTime);
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
