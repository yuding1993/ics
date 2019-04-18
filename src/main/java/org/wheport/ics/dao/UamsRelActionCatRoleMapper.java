package org.wheport.ics.dao;

import org.apache.ibatis.annotations.Param;
import org.wheport.ics.domain.pojo.UamsRelActionCatRole;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface UamsRelActionCatRoleMapper extends MySqlMapper<UamsRelActionCatRole>, Mapper<UamsRelActionCatRole> {
    List<UamsRelActionCatRole> selectAllowViewCat(@Param("userId") String userId);
}