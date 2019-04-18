package org.wheport.ics.dao;

import org.wheport.ics.domain.pojo.FriendLinkCat;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

@org.apache.ibatis.annotations.Mapper
public interface FriendLinkCatMapper extends MySqlMapper<FriendLinkCat>, Mapper<FriendLinkCat> {
}