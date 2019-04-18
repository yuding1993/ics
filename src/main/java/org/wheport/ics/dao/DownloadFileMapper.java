package org.wheport.ics.dao;

import org.wheport.ics.domain.pojo.DownloadFile;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

@org.apache.ibatis.annotations.Mapper
public interface DownloadFileMapper extends MySqlMapper<DownloadFile>, Mapper<DownloadFile> {
}