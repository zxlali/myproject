package com.zxl.test.myproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by Alex on 17/6/2 下午5:33.
 */
public class DTAuthenticationProcessingFilter extends AbstractSecurityInterceptor implements
    Filter {
    @Autowired
    private FilterInvocationSecurityMetadataSource securityMetadataSource;

    public DTAuthenticationProcessingFilter(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    @Autowired
    public void setDTAccessDecisionManager(DTAccessDecisionManager accessDecisionManager) {
        super.setAccessDecisionManager(accessDecisionManager);

    }


    @Override public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.securityMetadataSource;
    }

    @Override public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
        FilterChain filterChain) throws IOException, ServletException {
        //fi里面有一个被拦截的url
        //里面调用MyInvocationSecurityMetadataSource的getAttributes(Object object)这个方法获取fi对应的所有权限
        //再调用MyAccessDecisionManager的decide方法来校验用户的权限是否足够
        FilterInvocation fi = new FilterInvocation(servletRequest, servletResponse, filterChain);
        InterceptorStatusToken token = super.beforeInvocation(fi);
        try {
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } finally {
            super.afterInvocation(token, null);
        }
    }

    @Override public void destroy() {

    }

}
