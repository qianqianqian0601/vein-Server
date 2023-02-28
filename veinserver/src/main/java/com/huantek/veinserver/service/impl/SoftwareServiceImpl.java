package com.huantek.veinserver.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSSClient;
import com.huantek.veinserver.dao.SoftwareDao;
import com.huantek.veinserver.model.JsonModel;
import com.huantek.veinserver.model.SoftwareVersionModel;
import com.huantek.veinserver.ossUtil.SoftwareOSSUtil.AliyunOssUpSoftwareVersion;
import com.huantek.veinserver.ossUtil.firmOSSUtil.AliyunOssUpFirmVersion;
import com.huantek.veinserver.service.SoftwareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Service
public class SoftwareServiceImpl implements SoftwareService {

    @Autowired
    private SoftwareDao softwareDao;

    @Override
    public String softwareVersionUP(MultipartFile softwareFile, String softwareVersionName, float softwareVersionCode, String softwareVersionInfo) {
        JsonModel jsonModel = new JsonModel();
        try {
            //判断参数是否为NULL
            if (softwareFile==null || softwareVersionName==null || softwareVersionCode==0 || softwareVersionInfo==null){
                jsonModel.setCurrentTime(System.currentTimeMillis());
                jsonModel.setErrorCode(1004);
                jsonModel.setErrorMessage("值异常");
                jsonModel.setSuccess(false);
                return JSONObject.toJSONString(jsonModel);
            }
            //上传文件到OSS
            OSSClient ossClient = AliyunOssUpSoftwareVersion.getOSSClient();
            String url = AliyunOssUpSoftwareVersion.save(softwareFile, ossClient);
            float softwareSize = Float.parseFloat(String.valueOf(softwareFile.getSize()))/1024/1024;
            //存储文件信息以及路径
            softwareDao.softwareVersionUP(url, softwareVersionName, softwareSize ,softwareVersionCode, softwareVersionInfo, System.currentTimeMillis());
            //返回json信息
            jsonModel.setCurrentTime(System.currentTimeMillis());
            jsonModel.setErrorCode(0);
            jsonModel.setErrorMessage("后端服务软件版本文件上传成功");
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
    public String softwareVersionDownLatest() {
        JsonModel jsonModel = new JsonModel();
        try {
            SoftwareVersionModel softwareVersionModel = softwareDao.softwareVersionDownLatest();
            if (softwareVersionModel==null){
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
                jsonModel.setDataObject(softwareVersionModel);
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
