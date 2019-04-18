package org.wheport.ics.dao;

import org.wheport.ics.domain.pojo.PressCat;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

@org.apache.ibatis.annotations.Mapper
public interface PressCatMapper extends MySqlMapper<PressCat>, Mapper<PressCat> {
}