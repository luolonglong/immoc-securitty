package com.imooc.security.core.authentication.mobile;

import lombok.Data;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 处理传递过来的token
 * @author Gatsby.Luo
 * @date 2018-10-31
 */
@Data
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    /**
     * 进行身份认证的逻辑
     * 用@Link UserDetail 获取用户信息,然后重新组装已认证的AuthenticationToken
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserDetails userDetails = userDetailsService.loadUserByUsername((String) authentication.getPrincipal());

        //未认证的token
        SmsCodeAuthenticationToken authenticationToken = (SmsCodeAuthenticationToken) authentication;

        if (userDetails == null) {
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }
        //已认证的token
        SmsCodeAuthenticationToken authenticationResult =
                new SmsCodeAuthenticationToken(userDetails, userDetails.getAuthorities());
        //把未认证的token中的details信息拷贝到已认证的token中.
        authenticationResult.setDetails(authenticationToken.getDetails());

        return authenticationResult;
    }

    /**
     * AuthenticationManager 根据supports
     * 挑选对应的provider来处理传进来的token
     * @param authentication
     * @return
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
