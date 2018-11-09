package com.imooc.web.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * 通过过滤器,拦截URL
 * @author zhailiang
 */
@Component
public class TimeFilter implements Filter {

    @Override
    public void destroy() {
        System.out.println("time filter destroy");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        long start = System.currentTimeMillis();
        chain.doFilter(request, response);

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        System.out.println("time filter init");
    }

}
