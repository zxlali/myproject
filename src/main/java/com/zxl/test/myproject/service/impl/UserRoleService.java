package com.zxl.test.myproject.service.impl;

import com.zxl.test.myproject.dao.domain.RoleInfo;
import com.zxl.test.myproject.dao.domain.RoleInfoCriteria;
import com.zxl.test.myproject.dao.domain.UserRole;
import com.zxl.test.myproject.dao.domain.UserRoleCriteria;
import com.zxl.test.myproject.dao.mapper.RoleInfoMapper;
import com.zxl.test.myproject.dao.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Alex on 17/6/2 下午3:26.
 */
@Service
public class UserRoleService {
    @Autowired
    private UserRoleMapper userRoleMapper;

    public List<UserRole> getUserRolesByUserId(Integer userId) {
        UserRoleCriteria userRoleCriteria = new UserRoleCriteria();
        userRoleCriteria.createCriteria().andUserIdEqualTo(userId);
        return userRoleMapper.selectByExample(userRoleCriteria);
    }

}
