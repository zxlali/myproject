package com.zxl.test.myproject.mvc.intercepter.auth;

import com.zxl.test.myproject.annotation.Access;

import javax.servlet.http.HttpServletRequest;

public interface AccessFilter {
  void doFilter(HttpServletRequest request, Access access, AccessFilterChain accessFilterChain);
}
