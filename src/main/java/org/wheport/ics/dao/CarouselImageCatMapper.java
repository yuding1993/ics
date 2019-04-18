package org.wheport.ics.dao;

import org.wheport.ics.domain.pojo.CarouselImageCat;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

@org.apache.ibatis.annotations.Mapper
public interface CarouselImageCatMapper extends MySqlMapper<CarouselImageCat>, Mapper<CarouselImageCat> {
}