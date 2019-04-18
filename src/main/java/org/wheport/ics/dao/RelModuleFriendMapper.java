package org.wheport.ics.dao;

import org.wheport.ics.domain.pojo.RelModuleFriend;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

@org.apache.ibatis.annotations.Mapper
public interface RelModuleFriendMapper extends MySqlMapper<RelModuleFriend>, Mapper<RelModuleFriend> {
}