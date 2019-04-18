package org.wheport.ics.dao;

import org.wheport.ics.domain.pojo.ActionCat;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

@org.apache.ibatis.annotations.Mapper
public interface ActionCatMapper extends MySqlMapper<ActionCat>, Mapper<ActionCat> {
}