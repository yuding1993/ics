package org.wheport.ics.dao;

import org.apache.ibatis.annotations.Param;
import org.wheport.ics.domain.pojo.UamsRelActionRole;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface UamsRelActionRoleMapper extends MySqlMapper<UamsRelActionRole>, Mapper<UamsRelActionRole> {
    List<UamsRelActionRole> selectAllowAction(@Param("userId") String userId);
}