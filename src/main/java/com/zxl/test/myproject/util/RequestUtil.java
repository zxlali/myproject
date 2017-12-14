package com.zxl.test.myproject.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {

  private static Logger logger = LoggerFactory.getLogger(RequestUtil.class);
  private static final String REQUEST_PARAM_USERID = "userId";
  
  public static int getUserId(HttpServletRequest request) {
    int userId = 0;
    String userIdStr = request.getParameter(REQUEST_PARAM_USERID);
    try {
      userId = Integer.valueOf(userIdStr);
    } catch (Exception e) {
      logger.error(String.format("请求参数：userId >> %s format convert error", userId));
    }
    return userId;
  }

  public static void main(String[] args) {
    Md5PasswordEncoder bCryptPasswordEncoder = new Md5PasswordEncoder();
    System.out.println(bCryptPasswordEncoder.encodePassword("user", "salt-"));
  }
}
