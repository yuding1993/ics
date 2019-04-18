package org.wheport.ics.dao;

import org.wheport.ics.domain.pojo.ActionInfo;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

@org.apache.ibatis.annotations.Mapper
public interface ActionInfoMapper extends MySqlMapper<ActionInfo>, Mapper<ActionInfo> {
}