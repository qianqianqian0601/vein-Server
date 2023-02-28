package com.huantek.veinserver.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSSClient;
import com.huantek.veinserver.dao.FirmwareVersionDao;
import com.huantek.veinserver.model.FirmVersionModel;
import com.huantek.veinserver.model.JsonModel;
import com.huantek.veinserver.ossUtil.firmOSSUtil.AliyunOssUpFirmVersion;
import com.huantek.veinserver.service.FirmwareVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 创建人：wyx
 * 类说明：本类用作实现方法的逻辑处理
 * @author PS2-Inception
 */

@Service
public class FirmwareVersionImpl implements FirmwareVersionService {
    @Autowired
    private FirmwareVersionDao firmDao;

    //固件版本上传
    @Override
    public String firmwareVersionUP(MultipartFile firmFile, String firmVersionName, float firmVersionCode, String firmVersionInfo) {
        JsonModel jsonModel = new JsonModel();
        try {
            //判断参数是否为NULL
            if (firmFile==null || firmVersionName==null || firmVersionCode==0 || firmVersionInfo==null){
                jsonModel.setCurrentTime(System.currentTimeMillis());
                jsonModel.setErrorCode(1004);
                jsonModel.setErrorMessage("值异常");
                jsonModel.setSuccess(false);
                return JSONObject.toJSONString(jsonModel);
            }
            //上传文件到OSS
            OSSClient ossClient = AliyunOssUpFirmVersion.getOSSClient();
            String url = AliyunOssUpFirmVersion.save(firmFile, ossClient);
            //存储文件信息以及路径
            firmDao.firmwareVersionUP(url, firmVersionName, firmVersionCode, firmVersionInfo, System.currentTimeMillis());
            //返回json信息
            jsonModel.setCurrentTime(System.currentTimeMillis());
            jsonModel.setErrorCode(0);
            jsonModel.setErrorMessage("固件版本文件上传成功");
            jsonModel.setSuccess(true);
            return JSONObject.toJSONString(jsonModel);
        }catch (Exception e){
            e.printStackTrace();
            jsonModel.setCurrentTime(System.currentTimeMillis());
            jsonModel.setErrorCode(1002);
            jsonModel.setErrorMessage("服务异常");
            jsonModel.setSuccess(false);
            return JSONObject.toJSONString(jsonModel);
        }
    }

    //查询固件最新版本信息
    @Override
    public String firmwareVersionDownLatest() {
        JsonModel jsonModel = new JsonModel();
        try {
            FirmVersionModel firmVersionModel = firmDao.firmwareVersionDownLatest();
            if (firmVersionModel==null){
                jsonModel.setSuccess(true);
                jsonModel.setErrorCode(0);
                jsonModel.setCurrentTime(System.currentTimeMillis());
                jsonModel.setErrorMessage("服务器中暂时没有其他版本");
                return JSONObject.toJSONString(jsonModel);
            }else {
                jsonModel.setSuccess(true);
                jsonModel.setErrorCode(0);
                jsonModel.setCurrentTime(System.currentTimeMillis());
                jsonModel.setErrorMessage("最新版本信息");
                jsonModel.setDataObject(firmVersionModel);
                return JSONObject.toJSONString(jsonModel);
            }
        }catch (Exception e){
            jsonModel.setSuccess(false);
            jsonModel.setErrorCode(1002);
            jsonModel.setCurrentTime(System.currentTimeMillis());
            jsonModel.setDataObject(null);
            jsonModel.setErrorMessage("服务异常");
            return JSONObject.toJSONString(jsonModel);
        }
    }
}
