package com.zxl.test.myproject.exception;

import org.apache.commons.lang3.StringUtils;

public class AuthNotPassException extends RuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private String userId;

  public AuthNotPassException(String message) {
    super(message);
  }

  public AuthNotPassException(String message, String userId) {
    super(message);
    this.userId = userId;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  @Override
  public String getMessage() {
    if (StringUtils.isNotBlank(userId))
      return String.format("%s>>>userId:%s", super.getMessage(), userId);
    return super.getMessage();
  }
}
