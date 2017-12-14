package com.zxl.test.myproject.exception;

/**
 * 
 * @author Alex
 * @date 2016年4月21日下午8:05:53
 */
public class ServiceUnavailableException extends RuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public ServiceUnavailableException (String message) {
    super(message);
  }
  
  public ServiceUnavailableException(String message, Throwable cluase) {
    super(message, cluase);
  }
}
