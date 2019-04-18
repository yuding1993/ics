package org.wheport.ics.dao;

import org.wheport.ics.domain.pojo.GraphicsContext;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

@org.apache.ibatis.annotations.Mapper
public interface GraphicsContextMapper extends MySqlMapper<GraphicsContext>, Mapper<GraphicsContext> {
}