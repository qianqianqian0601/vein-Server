package com.huantek.veinserver.dao;

import com.huantek.veinserver.model.ServerVersionModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface SoftwareServerVersionDao {

    void ServerVersionUP(@Param("url") String url, @Param("name") String serverVersionName, @Param("size") float serverSize, @Param("code") float serverVersionCode, @Param("info") String serverVersionInfo, @Param("time") long currentTimeMillis);

    ServerVersionModel ServerVersionDownLatest();
}
