package org.wheport.ics.dao;

import org.wheport.ics.domain.pojo.RelModuleGraphics;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

@org.apache.ibatis.annotations.Mapper
public interface RelModuleGraphicsMapper extends MySqlMapper<RelModuleGraphics>, Mapper<RelModuleGraphics> {
}