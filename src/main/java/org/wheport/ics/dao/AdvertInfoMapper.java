package org.wheport.ics.dao;

import org.wheport.ics.domain.pojo.AdvertInfo;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

@org.apache.ibatis.annotations.Mapper
public interface AdvertInfoMapper extends MySqlMapper<AdvertInfo>, Mapper<AdvertInfo> {
}