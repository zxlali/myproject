package com.zxl.test.myproject.mvc.intercepter.auth;

import com.zxl.test.myproject.annotation.Access;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义过滤链
 * @author Alex
 * @date 2016年4月21日下午10:16:29
 */
public class AccessFilterChain {
  private final List<AccessFilter> filters;

  private int index = 0;

  public AccessFilterChain() {
    filters = new ArrayList<>();
  }

  public AccessFilterChain(List<AccessFilter> filters) {
    this.filters = filters;
  }
  
  public void addFilter(AccessFilter filter) {
    filters.add(filter);
  }
  
  public void doFilter(final HttpServletRequest request, final Access access) {
    if(index < this.filters.size()) {
      AccessFilter nextFilter = this.filters.get(index);
      nextFilter.doFilter(request, access, this);
      index++;
    }
  }
}
