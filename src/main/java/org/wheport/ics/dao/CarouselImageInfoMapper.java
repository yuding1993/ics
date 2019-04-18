package org.wheport.ics.dao;

import org.wheport.ics.domain.pojo.CarouselImageInfo;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

@org.apache.ibatis.annotations.Mapper
public interface CarouselImageInfoMapper extends MySqlMapper<CarouselImageInfo>, Mapper<CarouselImageInfo> {
}