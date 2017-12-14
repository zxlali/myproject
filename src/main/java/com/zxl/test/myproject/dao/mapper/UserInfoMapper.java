package com.zxl.test.myproject.dao.mapper;

import com.zxl.test.myproject.dao.domain.UserInfo;
import com.zxl.test.myproject.dao.domain.UserInfoCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface UserInfoMapper {
    int countByExample(UserInfoCriteria example);

    int deleteByExample(UserInfoCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    List<UserInfo> selectByExampleWithRowbounds(UserInfoCriteria example, RowBounds rowBounds);

    List<UserInfo> selectByExample(UserInfoCriteria example);

    UserInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserInfo record, @Param("example") UserInfoCriteria example);

    int updateByExample(@Param("record") UserInfo record, @Param("example") UserInfoCriteria example);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
}