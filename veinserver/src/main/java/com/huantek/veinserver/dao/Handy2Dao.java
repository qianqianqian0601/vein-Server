package com.huantek.veinserver.dao;

import com.huantek.veinserver.model.Handy2VersionModel;
import com.huantek.veinserver.model.SoftwareVersionModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface Handy2Dao {
    void handy2VersionUP(@Param("url") String url, @Param("name") String handy2VersionName, @Param("code") float handy2VersionCode, @Param("info") String handy2VersionInfo, @Param("time") long currentTimeMillis);

    Handy2VersionModel handy2VersionDownLatest();
}
