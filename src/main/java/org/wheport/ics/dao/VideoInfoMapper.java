package org.wheport.ics.dao;

import org.wheport.ics.domain.pojo.VideoInfo;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

@org.apache.ibatis.annotations.Mapper
public interface VideoInfoMapper extends MySqlMapper<VideoInfo>, Mapper<VideoInfo> {
}