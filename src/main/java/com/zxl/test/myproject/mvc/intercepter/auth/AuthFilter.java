package com.zxl.test.myproject.mvc.intercepter.auth;

import com.zxl.test.myproject.annotation.Access;
import com.zxl.test.myproject.context.AppContext;
import com.zxl.test.myproject.exception.AuthNotPassException;
import com.zxl.test.myproject.util.RequestUtil;

import javax.servlet.http.HttpServletRequest;

public class AuthFilter extends AbstractAccessFilter {

  @Override
  protected void doInternalFilter(HttpServletRequest request, Access access) {
    int userId = RequestUtil.getUserId(request);
    if (userId < 0)
      throw new AuthNotPassException(String.format("Acess denied! userId>>%s is invalid", userId));
    AppContext.setUserId(userId);
  }

  @Override
  protected boolean isSupport(Access access) {
    return access == null || access.support() == Access.Type.ALL || access.support() == Access.Type.AUTH;
  }


}
