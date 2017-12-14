package com.zxl.test.myproject.service;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alex on 17/6/2 下午5:28.
 */
public class DTAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private String defaultFailureUrl;

    public DTAuthenticationFailureHandler(String defaultFailureUrl) {
        this.defaultFailureUrl = defaultFailureUrl;
    }
    @Override public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
        HttpServletResponse httpServletResponse, AuthenticationException e)
        throws IOException, ServletException {

        httpServletResponse.sendRedirect(defaultFailureUrl);

    }
}
