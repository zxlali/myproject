package com.zxl.test.myproject.dao.mapper;

import com.zxl.test.myproject.dao.domain.RoleInfo;
import com.zxl.test.myproject.dao.domain.RoleInfoCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface RoleInfoMapper {
    int countByExample(RoleInfoCriteria example);

    int deleteByExample(RoleInfoCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(RoleInfo record);

    int insertSelective(RoleInfo record);

    List<RoleInfo> selectByExampleWithRowbounds(RoleInfoCriteria example, RowBounds rowBounds);

    List<RoleInfo> selectByExample(RoleInfoCriteria example);

    RoleInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RoleInfo record, @Param("example") RoleInfoCriteria example);

    int updateByExample(@Param("record") RoleInfo record, @Param("example") RoleInfoCriteria example);

    int updateByPrimaryKeySelective(RoleInfo record);

    int updateByPrimaryKey(RoleInfo record);
}