package com.zxl.test.myproject.mvc.intercepter;

import com.zxl.test.myproject.annotation.Access;
import com.zxl.test.myproject.exception.ServiceUnavailableException;
import com.zxl.test.myproject.mvc.intercepter.auth.AccessFilterChain;
import com.zxl.test.myproject.mvc.intercepter.auth.AccessFilterFactory;
import com.zxl.test.myproject.util.AnnotationUtils;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拦截器
 * @author Alex
 * @date 2016年4月21日下午8:15:27
 */
public class AuthInterceptor implements HandlerInterceptor {

  private final AccessFilterFactory filterFactory;

  public AuthInterceptor(AccessFilterFactory filterFactory) {
    this.filterFactory = filterFactory;
  }

  @Override
  public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
      throws Exception {}

  @Override
  public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {

  }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    Access access = this.findAnnotation((HandlerMethod) handler, Access.class);
    if (access != null && access.mode() == Access.Mode.FORBIDDEN) {
      throw new ServiceUnavailableException("Access forbidden! - The service is unavailable.");
    }

    if (access == null || access.mode() == Access.Mode.PROTECTED) {
      AccessFilterChain accessFilterChain = filterFactory.createFilterChain();
      accessFilterChain.doFilter(request, access);
    }
    return false;
  }

  private <A extends Annotation> A findAnnotation(HandlerMethod method, Class<A> annotationType) {
    A annotation = method.getMethodAnnotation(annotationType);
    if (annotation == null) {
      Class<?> target = method.getBean().getClass();
      annotation = target.getAnnotation(annotationType);
      if (annotation == null) {
        annotation = AnnotationUtils.findAnnotation(new AnnotatedElement[] {method.getMethod(), target}, annotationType);
      }
    }
    return annotation;
  }

}
