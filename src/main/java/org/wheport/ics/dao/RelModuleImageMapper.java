package org.wheport.ics.dao;

import org.wheport.ics.domain.pojo.RelModuleImage;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

@org.apache.ibatis.annotations.Mapper
public interface RelModuleImageMapper extends MySqlMapper<RelModuleImage>, Mapper<RelModuleImage> {
}