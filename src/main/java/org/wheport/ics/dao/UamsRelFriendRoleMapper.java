package org.wheport.ics.dao;

import org.wheport.ics.domain.pojo.UamsRelFriendRole;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.Map;

@org.apache.ibatis.annotations.Mapper
public interface UamsRelFriendRoleMapper extends MySqlMapper<UamsRelFriendRole>, Mapper<UamsRelFriendRole> {
    Long findRelFriendLinkCount(Map<String, Object> paramMap);
}