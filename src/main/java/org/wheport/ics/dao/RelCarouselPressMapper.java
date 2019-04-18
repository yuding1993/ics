package org.wheport.ics.dao;

import org.wheport.ics.domain.pojo.RelCarouselPress;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

@org.apache.ibatis.annotations.Mapper
public interface RelCarouselPressMapper extends MySqlMapper<RelCarouselPress>, Mapper<RelCarouselPress> {
}