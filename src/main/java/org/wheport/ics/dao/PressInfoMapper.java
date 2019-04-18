package org.wheport.ics.dao;

import org.wheport.ics.domain.pojo.PressInfo;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

@org.apache.ibatis.annotations.Mapper
public interface PressInfoMapper extends MySqlMapper<PressInfo>, Mapper<PressInfo> {
}