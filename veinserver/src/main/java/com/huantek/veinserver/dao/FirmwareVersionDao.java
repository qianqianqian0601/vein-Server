package com.huantek.veinserver.dao;

import com.huantek.veinserver.model.FirmVersionModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 创建人：wyx
 * 类说明：通过本类操作数据库数据
 * @author PS2-Inception
 */
@Repository
@Mapper
public interface FirmwareVersionDao {
    //保存固件版本信息和地址
    void firmwareVersionUP(@Param("url")String url, @Param("firmVersionName")String firmVersionName, @Param("firmVersionCode")float firmVersionCode, @Param("firmVersionInfo")String firmVersionInfo, @Param("uploadTime")long currentTimeMillis);

    //查询固件最新版本的信息
    FirmVersionModel firmwareVersionDownLatest();
}
