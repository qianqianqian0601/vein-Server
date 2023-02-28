package com.huantek.veinserver.service;

import org.springframework.web.multipart.MultipartFile;

public interface InnerFirmwareVersionService {

    /**
     * 接口作用：上传固件版本
     * @param firmFile 固件文件
     * @param firmVersionName 固件版本名称
     * @param firmVersionCode 固件版本号
     * @param firmVersionInfo 固件版本说明
     * @return String
     */
    String firmwareVersionUP(MultipartFile firmFile, String firmVersionName, float firmVersionCode, String firmVersionInfo);

    /**
     * 接口作用：查询固件最新版本信息
     * @return
     */
    String firmwareVersionDownLatest();
}
