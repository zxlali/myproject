package com.zxl.test.myproject.dao.mapper;

import com.zxl.test.myproject.dao.domain.PermissionInfo;
import com.zxl.test.myproject.dao.domain.PermissionInfoCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface PermissionInfoMapper {
    int countByExample(PermissionInfoCriteria example);

    int deleteByExample(PermissionInfoCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(PermissionInfo record);

    int insertSelective(PermissionInfo record);

    List<PermissionInfo> selectByExampleWithRowbounds(PermissionInfoCriteria example, RowBounds rowBounds);

    List<PermissionInfo> selectByExample(PermissionInfoCriteria example);

    PermissionInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PermissionInfo record, @Param("example") PermissionInfoCriteria example);

    int updateByExample(@Param("record") PermissionInfo record, @Param("example") PermissionInfoCriteria example);

    int updateByPrimaryKeySelective(PermissionInfo record);

    int updateByPrimaryKey(PermissionInfo record);
}