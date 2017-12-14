package com.zxl.test.myproject.service.impl;

import com.zxl.test.myproject.dao.domain.RolePermission;
import com.zxl.test.myproject.dao.domain.RolePermissionCriteria;
import com.zxl.test.myproject.dao.mapper.RolePermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Alex on 17/6/7 上午11:44.
 */
@Service
public class RolePermissionService {
    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    public List<RolePermission> findRolePermission(List<Integer> roleIds) {
        RolePermissionCriteria rolePermissionCriteria = new RolePermissionCriteria();
        rolePermissionCriteria.createCriteria().andRoleIdIn(roleIds);
        return rolePermissionMapper.selectByExample(rolePermissionCriteria);
    }

    public List<Integer> findRolePermissionByPermissionId(List<Integer> permissionIds) {
        RolePermissionCriteria rolePermissionCriteria = new RolePermissionCriteria();
        rolePermissionCriteria.createCriteria().andPermissionIdIn(permissionIds);
        List<RolePermission> list = rolePermissionMapper.selectByExample(rolePermissionCriteria);
        return list.stream().map(RolePermission::getRoleId).distinct().collect(Collectors.toList());
    }
}
