package org.wheport.ics.dao;

import org.wheport.ics.domain.pojo.UamsUserRole;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

@org.apache.ibatis.annotations.Mapper
public interface UamsUserRoleMapper extends MySqlMapper<UamsUserRole>, Mapper<UamsUserRole> {
}