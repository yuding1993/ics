package org.wheport.ics.dao;

import org.wheport.ics.domain.pojo.ModuleInfo;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

@org.apache.ibatis.annotations.Mapper
public interface ModuleInfoMapper extends MySqlMapper<ModuleInfo>, Mapper<ModuleInfo> {
}