package org.wheport.ics.dao;

import org.wheport.ics.domain.pojo.RelModuleAdvert;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

@org.apache.ibatis.annotations.Mapper
public interface RelModuleAdvertMapper extends MySqlMapper<RelModuleAdvert>, Mapper<RelModuleAdvert> {
}