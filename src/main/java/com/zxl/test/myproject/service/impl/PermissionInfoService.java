package com.zxl.test.myproject.service.impl;

import com.zxl.test.myproject.dao.domain.PermissionInfo;
import com.zxl.test.myproject.dao.domain.PermissionInfoCriteria;
import com.zxl.test.myproject.dao.mapper.PermissionInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Alex on 17/6/7 上午11:14.
 */
@Service
public class PermissionInfoService {
    @Autowired
    private PermissionInfoMapper permissionInfoMapper;

    public List<PermissionInfo> findAll() {
        PermissionInfoCriteria permissionInfoCriteria = new PermissionInfoCriteria();
        permissionInfoCriteria.createCriteria();
        return permissionInfoMapper.selectByExample(permissionInfoCriteria);
    }

    public List<PermissionInfo> findPermissionByIds(List<Integer> ids) {
        PermissionInfoCriteria permissionInfoCriteria = new PermissionInfoCriteria();
        permissionInfoCriteria.createCriteria().andIdIn(ids);
        return permissionInfoMapper.selectByExample(permissionInfoCriteria);
    }
}
