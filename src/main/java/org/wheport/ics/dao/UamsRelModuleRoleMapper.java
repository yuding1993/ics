package org.wheport.ics.dao;

import org.wheport.ics.domain.pojo.UamsRelModuleRole;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.Map;

@org.apache.ibatis.annotations.Mapper
public interface UamsRelModuleRoleMapper extends MySqlMapper<UamsRelModuleRole>, Mapper<UamsRelModuleRole> {
    Long findRelModuleRoleCount(Map<String, Object> map);
}