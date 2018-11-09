package com.imooc.security.app.social.openid;

import lombok.Data;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.security.SocialUserDetailsService;

import java.util.HashSet;
import java.util.Set;

/**
 * 验证传进来的Token
 * @author Gatsby.Luo
 * @date 2018-11-09
 */
@Data
public class OpenIdAuthenticationProvider implements AuthenticationProvider {

    private UsersConnectionRepository usersConnectionRepository;

    private SocialUserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        //未认证的token.
        OpenIdAuthenticationToken authenticationToken = (OpenIdAuthenticationToken) authentication;

        //第一步 在数据库查询是否有对应记录.
        Set<String> providerUserIds = new HashSet<>();
        providerUserIds.add((String) authenticationToken.getPrincipal());
        Set<String> userIds = usersConnectionRepository
                .findUserIdsConnectedTo(authenticationToken.getProviderId(), providerUserIds);

        if (CollectionUtils.isEmpty(userIds) || userIds.size() != 1) {
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }

        String userId = userIds.iterator().next();

        //第二步 根据userId查询对应的UserDetails,构建对应的认证Token
        UserDetails user = userDetailsService.loadUserByUserId(userId);

        if (user == null) {
            throw new InternalAuthenticationServiceException("无法获取用户信息.");
        }

        OpenIdAuthenticationToken authenticationResult =
                new OpenIdAuthenticationToken(user, user.getAuthorities());

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
        return OpenIdAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
