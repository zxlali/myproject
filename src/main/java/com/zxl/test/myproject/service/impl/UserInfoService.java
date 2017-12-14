package com.zxl.test.myproject.service.impl;

import com.zxl.test.myproject.dao.domain.UserInfo;
import com.zxl.test.myproject.dao.domain.UserInfoCriteria;
import com.zxl.test.myproject.dao.mapper.UserInfoMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by Alex on 17/6/2 下午3:19.
 */
@Service
public class UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    public UserInfo getUserInfoByUserName(String userName) {
        UserInfoCriteria userInfoCriteria = new UserInfoCriteria();
        userInfoCriteria.createCriteria().andUserNameEqualTo(userName);
        List<UserInfo> userInfos = userInfoMapper.
            selectByExampleWithRowbounds(userInfoCriteria, new RowBounds(0, 1));
        if (CollectionUtils.isEmpty(userInfos)) {
            return null;
        }
        return userInfos.get(0);
    }
}
