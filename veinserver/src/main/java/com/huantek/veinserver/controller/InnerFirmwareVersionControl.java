package com.huantek.veinserver.controller;

import com.huantek.veinserver.service.FirmwareVersionService;
import com.huantek.veinserver.service.InnerFirmwareVersionService;
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
@RequestMapping("InnerFirm")
public class InnerFirmwareVersionControl {

    @Autowired
    private InnerFirmwareVersionService innerFirmService;

    /**
     * 接口作用：上传固件版本
     * @param firmFile 固件文件
     * @param firmVersionName 固件版本名称
     * @param firmVersionCode 固件版本号
     * @param firmVersionInfo 固件版本说明
     * @return
     */
    @RequestMapping(method = RequestMethod.POST,value = "/firmwareVersionUP")
    @ResponseBody
    public String firmwareVersionUP(MultipartFile firmFile, String firmVersionName, float firmVersionCode, String firmVersionInfo){
        String json = innerFirmService.firmwareVersionUP(firmFile, firmVersionName, firmVersionCode, firmVersionInfo);
        return json;
    }

    /**
     * 接口作用：查询固件最新版本信息
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "/firmwareVersionDownLatest")
    @ResponseBody
    public String firmwareVersionDownLatest(){
        String json = innerFirmService.firmwareVersionDownLatest();
        return json;
    }

}
