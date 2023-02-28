package com.huantek.veinserver.controller;

import com.huantek.veinserver.service.Handy2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * 创建人：wyx
 * 类说明：固件版本的操作
 * @author wyx
 */
@Controller
@RequestMapping("Handy2")
public class Handy2Controller {

    @Autowired
    private Handy2Service handy2Service;

    /**
     * 接口作用：上传固件版本
     * @param handy2File 固件文件
     * @param handy2VersionName 固件版本名称
     * @param handy2VersionCode 固件版本号
     * @param handy2VersionInfo 固件版本说明
     * @return
     */
    @RequestMapping(method = RequestMethod.POST,value = "/handy2VersionUP")
    @ResponseBody
    public String handy2VersionUP(MultipartFile handy2File, String handy2VersionName, float handy2VersionCode, String handy2VersionInfo){
        String json = handy2Service.handy2VersionUP(handy2File, handy2VersionName, handy2VersionCode, handy2VersionInfo);
        return json;
    }

    /**
     * 接口作用：查询固件最新版本信息
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "/handy2VersionDownLatest")
    @ResponseBody
    public String handy2VersionDownLatest(){
        String json = handy2Service.handy2VersionDownLatest();
        return json;
    }
}
