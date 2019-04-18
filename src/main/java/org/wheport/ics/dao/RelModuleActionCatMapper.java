package org.wheport.ics.dao;

import org.wheport.ics.domain.pojo.RelModuleActionCat;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

@org.apache.ibatis.annotations.Mapper
public interface RelModuleActionCatMapper extends MySqlMapper<RelModuleActionCat>, Mapper<RelModuleActionCat> {
}