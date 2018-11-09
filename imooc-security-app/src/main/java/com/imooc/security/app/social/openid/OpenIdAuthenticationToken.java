package com.imooc.security.app.social.openid;

import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author Gatsby.Luo
 * @date 2018-11-08
 */
@Getter
public class OpenIdAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = -3483587120343942235L;

    /** 主要内容principal中获取openId */
    private Object principal;
    /** 提供商id */
    private String providerId;

    /** 未认证的token */
    public OpenIdAuthenticationToken(String openId, String providerId) {
        super(null);
        this.principal = openId;
        this.providerId = providerId;
        setAuthenticated(false);
    }

    public OpenIdAuthenticationToken(Object principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        super.setAuthenticated(true); // must use super, as we override
    }

    /** 证书 */
    @Override
    public Object getCredentials() {
        return null;
    }
}
