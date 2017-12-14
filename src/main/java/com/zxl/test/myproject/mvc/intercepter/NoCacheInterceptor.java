package com.zxl.test.myproject.mvc.intercepter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alex on 17/6/6 上午9:58.
 */
public class NoCacheInterceptor implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub

    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws
        IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Cache-Control", "no-store");
        chain.doFilter(req, res);
    }

    public void destroy() {
        // TODO Auto-generated method stub

    }
}
