package org.wheport.ics.dao;

import org.wheport.ics.domain.pojo.SystemLog;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

@org.apache.ibatis.annotations.Mapper
public interface SystemLogMapper extends MySqlMapper<SystemLog>, Mapper<SystemLog> {
}