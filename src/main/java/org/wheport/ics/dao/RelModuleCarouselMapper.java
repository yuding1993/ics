package org.wheport.ics.dao;

import org.wheport.ics.domain.pojo.RelModuleCarousel;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

@org.apache.ibatis.annotations.Mapper
public interface RelModuleCarouselMapper extends MySqlMapper<RelModuleCarousel>, Mapper<RelModuleCarousel> {
}