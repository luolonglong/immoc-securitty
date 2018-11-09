package com.imooc.security.core.authentication.mobile;

import lombok.Data;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * 实现自定义的短信AbstractAuthenticationToken
 * 把AbstractAuthenticationToken 代码拷贝过来,然后稍稍修改
 * @author Gatsby.Luo
 * @date 2018-10-30
 */
@Data
public class SmsCodeAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = 8979181194667368500L;

    /** 存储的对象 */
    private final Object principal;

    /**
     * 未认证的构造方法.
     * @param mobile
     */
    public SmsCodeAuthenticationToken(String mobile) {
        super(null);
        this.principal = mobile;
        setAuthenticated(false);
    }

    /**
     * 已认证的构造方法.
     * @param principal   存储的对象
     * @param authorities
     */
    public SmsCodeAuthenticationToken(Object principal,
                                      Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        super.setAuthenticated(true); // must use super, as we override
    }

    @Override
    public Object getCredentials() {
        return null;
    }
}
