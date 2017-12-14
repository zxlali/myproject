package com.zxl.test.myproject.mvc.intercepter.auth;

import com.zxl.test.myproject.annotation.Access;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author Alex
 * @date 2016年4月21日下午10:23:29
 */
public abstract class AbstractAccessFilter implements AccessFilter {

  protected abstract void doInternalFilter(HttpServletRequest request, Access access);

  protected abstract boolean isSupport(Access access);

  protected void afterCompletion(HttpServletRequest request, Access access) {}

  @Override
  public void doFilter(HttpServletRequest request, Access access, AccessFilterChain accessFilterChain) {
    if (this.isSupport(access)) {
      doInternalFilter(request, access);
    }
    accessFilterChain.doFilter(request, access);

    afterCompletion(request, access);
  }

}
