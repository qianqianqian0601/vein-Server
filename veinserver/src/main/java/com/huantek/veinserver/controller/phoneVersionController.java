package com.huantek.veinserver.controller;

import com.huantek.veinserver.service.PhoneVersionService;
import com.huantek.veinserver.service.SoftwareServerVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/PhoneVersion")
public class phoneVersionController {

    @Autowired
    private PhoneVersionService phoneVersionService;


    /**
     * 接口作用：上传软件版本
     * @param PhoneFile 软件文件
     * @param PhoneVersionName 软件版本名称
     * @param PhoneVersionCode 软件版本号
     * @param PhoneVersionInfo 软件版本说明
     * @return
     */
    @RequestMapping(method = RequestMethod.POST,value = "/PhoneVersionUP")
    @ResponseBody
    public String firmwareVersionUP(MultipartFile PhoneFile, String PhoneVersionName, float PhoneVersionCode, String PhoneVersionInfo){
        String json = phoneVersionService.PhoneVersionUP(PhoneFile, PhoneVersionName, PhoneVersionCode, PhoneVersionInfo);
        return json;
    }

    /**
     * 接口作用：查询固件最新版本信息
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "/PhoneVersionDownLatest")
    @ResponseBody
    public String firmwareVersionDownLatest(){
        String json = phoneVersionService.PhoneVersionDownLatest();
        return json;
    }
}
