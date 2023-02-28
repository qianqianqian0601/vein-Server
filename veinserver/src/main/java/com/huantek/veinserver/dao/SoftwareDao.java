package com.huantek.veinserver.dao;

import com.huantek.veinserver.model.SoftwareVersionModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface SoftwareDao {
    void softwareVersionUP(@Param("url") String url, @Param("name") String softwareVersionName, @Param("size") float softwareSize, @Param("code") float softwareVersionCode, @Param("info") String softwareVersionInfo, @Param("time") long currentTimeMillis);

    SoftwareVersionModel softwareVersionDownLatest();
}
