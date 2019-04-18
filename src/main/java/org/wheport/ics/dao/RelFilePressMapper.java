package org.wheport.ics.dao;

import org.wheport.ics.domain.pojo.RelFilePress;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

@org.apache.ibatis.annotations.Mapper
public interface RelFilePressMapper extends MySqlMapper<RelFilePress>, Mapper<RelFilePress> {
}