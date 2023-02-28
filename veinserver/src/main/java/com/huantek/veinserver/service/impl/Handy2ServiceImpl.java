package com.huantek.veinserver.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSSClient;
import com.huantek.veinserver.dao.Handy2Dao;
import com.huantek.veinserver.model.Handy2VersionModel;
import com.huantek.veinserver.model.JsonModel;
import com.huantek.veinserver.model.SoftwareVersionModel;
import com.huantek.veinserver.ossUtil.handy2OSSUtil.AliyunOssUpHandy2Version;
import com.huantek.veinserver.service.Handy2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class Handy2ServiceImpl implements Handy2Service {

    @Autowired
    private Handy2Dao handy2Dao;

    @Override
    public String handy2VersionUP(MultipartFile handy2File, String handy2VersionName, float handy2VersionCode, String handy2VersionInfo) {
        JsonModel jsonModel = new JsonModel();
        try {
            //判断参数是否为NULL
            if (handy2File==null || handy2VersionName==null || handy2VersionCode==0 || handy2VersionInfo==null){
                jsonModel.setCurrentTime(System.currentTimeMillis());
                jsonModel.setErrorCode(1004);
                jsonModel.setErrorMessage("值异常");
                jsonModel.setSuccess(false);
                return JSONObject.toJSONString(jsonModel);
            }
            //上传文件到OSS
            OSSClient ossClient = AliyunOssUpHandy2Version.getOSSClient();
            String url = AliyunOssUpHandy2Version.save(handy2File, ossClient);
            handy2Dao.handy2VersionUP(url, handy2VersionName,handy2VersionCode, handy2VersionInfo, System.currentTimeMillis());
            //返回json信息
            jsonModel.setCurrentTime(System.currentTimeMillis());
            jsonModel.setErrorCode(0);
            jsonModel.setErrorMessage("Handy2版本文件上传成功");
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
    public String handy2VersionDownLatest() {
        JsonModel jsonModel = new JsonModel();
        try {
            Handy2VersionModel handy2VersionModel = handy2Dao.handy2VersionDownLatest();
            if (handy2VersionModel==null){
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
                jsonModel.setDataObject(handy2VersionModel);
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
