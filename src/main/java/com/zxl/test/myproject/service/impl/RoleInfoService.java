package com.zxl.test.myproject.service.impl;

import com.zxl.test.myproject.dao.domain.RoleInfo;
import com.zxl.test.myproject.dao.domain.RoleInfoCriteria;
import com.zxl.test.myproject.dao.domain.UserRole;
import com.zxl.test.myproject.dao.mapper.RoleInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Alex on 17/6/5 下午11:31.
 */
@Service
public class RoleInfoService {
    @Autowired
    private RoleInfoMapper roleInfoMapper;
    @Autowired
    private UserRoleService userRoleService;


    public List<String> getAllRoleInfoByUserId(Integer userId) {
        List<Integer> roles = userRoleService.getUserRolesByUserId(userId).stream()
            .map(UserRole::getRoleId).distinct().collect(
                Collectors.toList());
        return getRoleNameByIds(roles);
    }

    public RoleInfo getRoleInfoById(int id) {
        return roleInfoMapper.selectByPrimaryKey(id);
    }


    public List<RoleInfo> getAllRoleInfo() {
        RoleInfoCriteria roleInfoCriteria = new RoleInfoCriteria();
        roleInfoCriteria.createCriteria();
        return roleInfoMapper.selectByExample(roleInfoCriteria);
    }

    public List<String> getRoleNameByIds(List<Integer> roleIds) {
        RoleInfoCriteria roleInfoCriteria = new RoleInfoCriteria();
        roleInfoCriteria.createCriteria().andIdIn(roleIds);
        List<RoleInfo> list = roleInfoMapper.selectByExample(roleInfoCriteria);
        return list.stream().map(RoleInfo::getRoleName).collect(Collectors.toList());
    }
}
