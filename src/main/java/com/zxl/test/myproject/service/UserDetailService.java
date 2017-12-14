package com.zxl.test.myproject.service;

import com.zxl.test.myproject.config.CustUserDetail;
import com.zxl.test.myproject.dao.domain.RoleInfo;
import com.zxl.test.myproject.dao.domain.UserInfo;
import com.zxl.test.myproject.service.impl.RoleInfoService;
import com.zxl.test.myproject.service.impl.UserInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.stream.Collectors;

/**
 * Created by Alex on 17/6/2 下午3:01.
 *
 *
 * 将该用户能关联到的所有资源id集合赋值给用户实体
 */
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private RoleInfoService roleInfoService;

    @Override public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        if (StringUtils.isNotBlank(userName)) {
            UserInfo userInfo = userInfoService.getUserInfoByUserName(userName);
            if (userInfo == null)
                throw new UsernameNotFoundException("用户名[" + userName + "]不存在");

            CustUserDetail custUserDetail = new CustUserDetail();
            custUserDetail.setUsername(userName);
            custUserDetail.setPassword(userInfo.getPwd());
            custUserDetail.setEnable(userInfo.getStatus());
            //为用户加载权限
            custUserDetail.setAuthorities(userInfo.getId() == 1 ? roleInfoService.getAllRoleInfo()
                .stream().map(RoleInfo::getRoleName).collect(Collectors.toList())
                : roleInfoService.getAllRoleInfoByUserId(userInfo.getId()));

            return custUserDetail;
        }

        return null;
    }
}
