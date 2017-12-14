package com.zxl.test.myproject.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alex on 17/6/2 下午5:25.
 */
public class DTAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private static final Logger logger = LoggerFactory.getLogger(DTAuthenticationSuccessHandler.class);
    private String defaultTargetUrl;

    public DTAuthenticationSuccessHandler(String defaultTargetUrl) {
        this.defaultTargetUrl = defaultTargetUrl;
    }

    @Override public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
        HttpServletResponse httpServletResponse, Authentication authentication)
        throws IOException, ServletException {
        httpServletResponse.sendRedirect(defaultTargetUrl);
        logger.debug("[login successfully!]");
    }
}
