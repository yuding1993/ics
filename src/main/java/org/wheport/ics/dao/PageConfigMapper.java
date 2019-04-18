package org.wheport.ics.dao;

import org.wheport.ics.domain.pojo.PageConfig;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

@org.apache.ibatis.annotations.Mapper
public interface PageConfigMapper extends MySqlMapper<PageConfig>, Mapper<PageConfig> {
}