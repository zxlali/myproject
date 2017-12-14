package com.zxl.test.myproject.mvc.intercepter.auth;

import java.util.List;

/**
 * 自定义过滤器Factory
 * @author Alex
 * @date 2016年4月21日下午10:08:11
 */
public class AccessFilterFactory {
  private List<AccessFilter> filters;

  public AccessFilterFactory() {}

  public AccessFilterFactory(List<AccessFilter> filters) {
    this.filters = filters;
  }

  public AccessFilterChain createFilterChain() {
    return new AccessFilterChain(filters);
  }

  public List<AccessFilter> getFilters() {
    return filters;
  }

  public void setFilters(List<AccessFilter> filters) {
    this.filters = filters;
  }

}
