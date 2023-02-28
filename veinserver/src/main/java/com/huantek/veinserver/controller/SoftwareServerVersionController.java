package com.huantek.veinserver.controller;

import com.huantek.veinserver.service.SoftwareServerVersionService;
import com.huantek.veinserver.service.SoftwareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/serverVersion")
public class SoftwareServerVersionController {

    @Autowired
    private SoftwareServerVersionService softwareServerVersionService;


    /**
     * 接口作用：上传软件版本
     * @param ServerFile 软件文件
     * @param ServerVersionName 软件版本名称
     * @param ServerVersionCode 软件版本号
     * @param ServerVersionInfo 软件版本说明
     * @return
     */
    @RequestMapping(method = RequestMethod.POST,value = "/ServerVersionUP")
    @ResponseBody
    public String firmwareVersionUP(MultipartFile ServerFile, String ServerVersionName, float ServerVersionCode, String ServerVersionInfo){
        String json = softwareServerVersionService.ServerVersionUP(ServerFile, ServerVersionName, ServerVersionCode, ServerVersionInfo);
        return json;
    }

    /**
     * 接口作用：查询固件最新版本信息
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "/ServerVersionDownLatest")
    @ResponseBody
    public String firmwareVersionDownLatest(){
        String json = softwareServerVersionService.ServerVersionDownLatest();
        return json;
    }
}
