package org.wheport.ics.dao;

import org.wheport.ics.domain.pojo.UamsRelUserRole;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

@org.apache.ibatis.annotations.Mapper
public interface UamsRelUserRoleMapper extends MySqlMapper<UamsRelUserRole>, Mapper<UamsRelUserRole> {
}