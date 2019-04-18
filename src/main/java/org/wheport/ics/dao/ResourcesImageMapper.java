package org.wheport.ics.dao;

import org.wheport.ics.domain.pojo.ResourcesImage;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

@org.apache.ibatis.annotations.Mapper
public interface ResourcesImageMapper extends MySqlMapper<ResourcesImage>, Mapper<ResourcesImage> {
}