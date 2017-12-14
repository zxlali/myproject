package com.zxl.test.myproject.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author Alex
 * @date 2016年4月21日下午8:09:12
 */
@Target(value = {ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Access {

  Mode mode() default Mode.PROTECTED;

  Type support() default Type.ALL;

  enum Mode {
    PROTECTED, IGNORE, FORBIDDEN
  }
  enum Type {
    ALL, AUTH, LIMIT
  }

  enum LimitType {
    IP, UID
  }
}
