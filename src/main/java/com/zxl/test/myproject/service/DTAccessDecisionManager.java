package com.zxl.test.myproject.service;

import com.zxl.test.myproject.service.impl.RoleInfoService;
import com.zxl.test.myproject.service.impl.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Alex on 17/6/2 下午4:15.
 * <p>
 * 判断用户是否拥有所访问资源需要的权限
 */
@Component public class DTAccessDecisionManager implements AccessDecisionManager {
    @Autowired
    private RolePermissionService rolePermissionService;
    @Autowired
    private RoleInfoService roleInfoService;

    @Override public void decide(Authentication authentication, Object o,
        Collection<ConfigAttribute> configAttributes)
        throws AccessDeniedException, InsufficientAuthenticationException {
        //如果本系统没有需要授权的资源,则直接通过校验
        if (CollectionUtils.isEmpty(configAttributes)) {
            return;
        }

        List<Integer> permissionIds = configAttributes.stream().map(ConfigAttribute::getAttribute)
            .map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> roleIds = rolePermissionService.findRolePermissionByPermissionId(permissionIds);

        if (CollectionUtils.isEmpty(roleIds))
            return;
        List<String> roleNames =roleInfoService.getRoleNameByIds(roleIds);

        for (String role : roleNames) {
            for (GrantedAuthority ga : authentication.getAuthorities()) {
                if (role.trim().equals(ga.getAuthority())) {
                    return;
                }
            }
        }

        throw new AccessDeniedException("没有进行该操作的权限!");
    }

    @Override public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override public boolean supports(Class<?> aClass) {
        return true;
    }
}
