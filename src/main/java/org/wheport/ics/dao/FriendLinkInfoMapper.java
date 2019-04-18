package org.wheport.ics.dao;

import org.wheport.ics.domain.pojo.FriendLinkInfo;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

@org.apache.ibatis.annotations.Mapper
public interface FriendLinkInfoMapper extends MySqlMapper<FriendLinkInfo>, Mapper<FriendLinkInfo> {
}