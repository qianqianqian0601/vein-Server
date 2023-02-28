package com.huantek.veinserver.controller;

import com.huantek.veinserver.service.SoftwareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("softwareVersion")
public class SoftwareVersionController {

    @Autowired
    private SoftwareService softwareService;


    /**
     * 接口作用：上传软件版本
     * @param firmFile 软件文件
     * @param SoftwareVersionName 软件版本名称
     * @param SoftwareVersionCode 软件版本号
     * @param SoftwareVersionInfo 软件版本说明
     * @return
     */
    @RequestMapping(method = RequestMethod.POST,value = "/softwareVersionUP")
    @ResponseBody
    public String firmwareVersionUP(MultipartFile softwareFile, String SoftwareVersionName, float SoftwareVersionCode, String SoftwareVersionInfo){
        String json = softwareService.softwareVersionUP(softwareFile, SoftwareVersionName, SoftwareVersionCode, SoftwareVersionInfo);
        return json;
    }

    /**
     * 接口作用：查询固件最新版本信息
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "/softwareVersionDownLatest")
    @ResponseBody
    public String firmwareVersionDownLatest(){
        String json = softwareService.softwareVersionDownLatest();
        return json;
    }

}
