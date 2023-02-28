package com.huantek.veinserver.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSSClient;
import com.huantek.veinserver.dao.SoftwareServerVersionDao;
import com.huantek.veinserver.model.JsonModel;
import com.huantek.veinserver.model.ServerVersionModel;
import com.huantek.veinserver.model.SoftwareVersionModel;
import com.huantek.veinserver.ossUtil.ServerOSSUtil.AliyunOssUpServerVersion;
import com.huantek.veinserver.ossUtil.SoftwareOSSUtil.AliyunOssUpSoftwareVersion;
import com.huantek.veinserver.ossUtil.firmOSSUtil.AliyunOssUpFirmVersion;
import com.huantek.veinserver.service.SoftwareServerVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class SoftwareServerVersionImpl implements SoftwareServerVersionService {

    @Autowired
    private SoftwareServerVersionDao softwareServerVersionDao;

    @Override
    public String ServerVersionUP(MultipartFile serverFile, String serverVersionName, float serverVersionCode, String serverVersionInfo) {
        JsonModel jsonModel = new JsonModel();
        try {
            //判断参数是否为NULL
            if (serverFile==null || serverVersionName==null || serverVersionCode==0 || serverVersionInfo==null){
                jsonModel.setCurrentTime(System.currentTimeMillis());
                jsonModel.setErrorCode(1004);
                jsonModel.setErrorMessage("值异常");
                jsonModel.setSuccess(false);
                return JSONObject.toJSONString(jsonModel);
            }
            //上传文件到OSS
            OSSClient ossClient = AliyunOssUpServerVersion.getOSSClient();
            String url = AliyunOssUpServerVersion.save(serverFile, ossClient);
            float serverSize = Float.parseFloat(String.valueOf(serverFile.getSize()))/1024/1024;
            //存储文件信息以及路径
            softwareServerVersionDao.ServerVersionUP(url, serverVersionName, serverSize ,serverVersionCode, serverVersionInfo, System.currentTimeMillis());
            //返回json信息
            jsonModel.setCurrentTime(System.currentTimeMillis());
            jsonModel.setErrorCode(0);
            jsonModel.setErrorMessage("软件版本文件上传成功");
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

    @Override
    public String ServerVersionDownLatest() {
        JsonModel jsonModel = new JsonModel();
        try {
            ServerVersionModel serverVersionModel = softwareServerVersionDao.ServerVersionDownLatest();
            if (serverVersionModel==null){
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
                jsonModel.setDataObject(serverVersionModel);
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
