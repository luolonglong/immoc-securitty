package com.imooc.security.app.social.openid;

import com.imooc.security.core.properties.SecurityConstants;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 社交登录处理器
 * @author Gatsby.Luo
 * @date 2018-11-08
 */
public class OpenIdAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private String openIdParameter = SecurityConstants.DEFAULT_PARAMETER_NAME_OPEN_ID;
    private String providerIdParameter = SecurityConstants.DEFAULT_PARAMETER_NAME_PROVIDER_ID;

    private boolean postOnly = true;

    public OpenIdAuthenticationFilter() {
        super(new AntPathRequestMatcher(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_OPEN_ID, "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if (this.postOnly && !"POST".equals(request.getMethod())) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {
            String openId = obtainOpenId(request);
            String providerId = obtainProviderId(request);

            if (openId == null) {
                openId = "";
            }
            if (providerId == null) {
                providerId = "";
            }

            openId = openId.trim();
            providerId = providerId.trim();

            OpenIdAuthenticationToken authRequest = new OpenIdAuthenticationToken(openId, providerId);

            setDetails(request, authRequest);

            return this.getAuthenticationManager().authenticate(authRequest);

        }
    }

    private void setDetails(HttpServletRequest request, OpenIdAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    private String obtainProviderId(HttpServletRequest request) {

        return request.getParameter(this.providerIdParameter);
    }

    private String obtainOpenId(HttpServletRequest request) {
        return request.getParameter(this.openIdParameter);
    }
}

