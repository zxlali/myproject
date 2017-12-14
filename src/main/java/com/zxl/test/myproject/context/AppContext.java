package com.zxl.test.myproject.context;

import com.zxl.test.myproject.dao.domain.UserInfo;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 
 * @author Alex
 * @date 2016年4月22日上午9:54:31
 */
public class AppContext implements ApplicationContextAware {
  private static final ThreadLocal<UserInfo> CUST_INFO_THREAD_LOCAL = new ThreadLocal<>();
  private static final ThreadLocal<Integer> USERID_THREAD_LOCAL = new ThreadLocal<>();

  private static ApplicationContext context;

  public static ApplicationContext getContext() {
    return context;
  }

  public static UserInfo getCustInfo() {
    return CUST_INFO_THREAD_LOCAL.get();
  }

  public static void setCustInfo(UserInfo custInfo) {
    CUST_INFO_THREAD_LOCAL.set(custInfo);
  }

  public static Integer getUserId() {
    return USERID_THREAD_LOCAL.get();
  }

  public static void setUserId(Integer userId) {
    USERID_THREAD_LOCAL.set(userId);
  }

  public static void clear() {
    CUST_INFO_THREAD_LOCAL.remove();
    USERID_THREAD_LOCAL.remove();
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    context = applicationContext;
  }

}
