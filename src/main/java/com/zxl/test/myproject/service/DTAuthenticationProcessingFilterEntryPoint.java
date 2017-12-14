package com.zxl.test.myproject.service;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alex on 17/6/2 下午5:41.
 */
public class DTAuthenticationProcessingFilterEntryPoint implements AuthenticationEntryPoint{
    @Override public void commence(HttpServletRequest httpServletRequest,
        HttpServletResponse httpServletResponse, AuthenticationException e)
        throws IOException, ServletException {
        httpServletResponse.sendError(
            HttpServletResponse.SC_UNAUTHORIZED,
            "Unauthorized: Authentication token was either missing or invalid.");
    }
}
