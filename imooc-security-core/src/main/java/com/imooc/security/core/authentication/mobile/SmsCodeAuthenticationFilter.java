package com.imooc.security.core.authentication.mobile;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.imooc.security.core.properties.SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE;
import static com.imooc.security.core.properties.SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE;

/**
 * 短信验证码 过滤器,
 * 参考UsernamePasswordAuthenticationFilter
 * @author Gatsby.Luo
 * @date 2018-10-30
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SmsCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private String mobileParameter = DEFAULT_PARAMETER_NAME_MOBILE;
    private boolean postOnly = true;

    public SmsCodeAuthenticationFilter() {
        super(new AntPathRequestMatcher(DEFAULT_LOGIN_PROCESSING_URL_MOBILE, "POST"));
    }

    /** 将参数传递至@link UserDetailsService */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (this.postOnly && !"POST".equals(request.getMethod())) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {
            String mobile = this.obtainMobile(request);

            if (mobile == null) {
                mobile = "";
            }

            mobile = mobile.trim();
            //未认证的token.
            SmsCodeAuthenticationToken authRequest = new SmsCodeAuthenticationToken(mobile);
            setDetails(request, authRequest);
            //认证token.
            Authentication authenticate = this.getAuthenticationManager().authenticate(authRequest);
            return authenticate;
        }
    }

    protected String obtainMobile(HttpServletRequest request) {
        return request.getParameter(this.mobileParameter);
    }

    protected void setDetails(HttpServletRequest request, SmsCodeAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }

}
