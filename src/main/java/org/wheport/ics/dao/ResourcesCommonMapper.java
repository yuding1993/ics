package org.wheport.ics.dao;

import org.wheport.ics.domain.pojo.ResourcesCommon;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

@org.apache.ibatis.annotations.Mapper
public interface ResourcesCommonMapper extends MySqlMapper<ResourcesCommon>, Mapper<ResourcesCommon> {
}