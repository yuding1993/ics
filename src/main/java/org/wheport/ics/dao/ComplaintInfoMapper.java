package org.wheport.ics.dao;

import org.wheport.ics.domain.pojo.ComplaintInfo;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

@org.apache.ibatis.annotations.Mapper
public interface ComplaintInfoMapper extends MySqlMapper<ComplaintInfo>, Mapper<ComplaintInfo> {
}