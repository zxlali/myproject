package com.zxl.test.myproject.dao.mapper;

import com.zxl.test.myproject.dao.domain.RolePermission;
import com.zxl.test.myproject.dao.domain.RolePermissionCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface RolePermissionMapper {
    int countByExample(RolePermissionCriteria example);

    int deleteByExample(RolePermissionCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(RolePermission record);

    int insertSelective(RolePermission record);

    List<RolePermission> selectByExampleWithRowbounds(RolePermissionCriteria example, RowBounds rowBounds);

    List<RolePermission> selectByExample(RolePermissionCriteria example);

    RolePermission selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RolePermission record, @Param("example") RolePermissionCriteria example);

    int updateByExample(@Param("record") RolePermission record, @Param("example") RolePermissionCriteria example);

    int updateByPrimaryKeySelective(RolePermission record);

    int updateByPrimaryKey(RolePermission record);
}