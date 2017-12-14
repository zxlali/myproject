package com.zxl.test.myproject.service;

import com.google.common.collect.Lists;
import com.zxl.test.myproject.dao.domain.PermissionInfo;
import com.zxl.test.myproject.service.impl.PermissionInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by Alex on 17/6/2 下午4:23.
 */
@Component
public class DTSecurityMetaDataSource implements FilterInvocationSecurityMetadataSource {
    private static final Logger LOGGER = LoggerFactory.getLogger(DTSecurityMetaDataSource.class);

    @Autowired
    private PermissionInfoService permissionInfoService;

    private Map<String, Collection<ConfigAttribute>> resourceMap = null;

    public DTSecurityMetaDataSource() {

    }

    private  void loadResourceDefine() {
        resourceMap = new HashMap<>();
        List<PermissionInfo> permissionInfos = permissionInfoService.findAll();
        for (PermissionInfo permissionInfo : permissionInfos) {
            Collection<ConfigAttribute> attributes = Lists.newArrayList();
            ConfigAttribute configAttribute = new SecurityConfig(String.valueOf(permissionInfo.getId()));
            attributes.add(configAttribute);
            resourceMap.put(permissionInfo.getPermissionUrl(), attributes);
        }
        LOGGER.info("security info load success!!");
    }

    @Override public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        //返回请求的资源需要的权限
        if (resourceMap == null) loadResourceDefine();
        HttpServletRequest request = ((FilterInvocation) o).getHttpRequest();

        Collection<ConfigAttribute> configAttributes = null;
        for (Map.Entry<String, Collection<ConfigAttribute>> entry  : resourceMap.entrySet()) {
            AntPathRequestMatcher matcher = new AntPathRequestMatcher(entry.getKey());
            if (matcher.matches(request)) {
                configAttributes = entry.getValue();
                break;
            }
        }
        return configAttributes;
    }

    @Override public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override public boolean supports(Class<?> aClass) {
        return true;
    }
}
