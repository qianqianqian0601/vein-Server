package com.huantek.veinserver.controller;

import com.alibaba.fastjson.JSONObject;
import com.huantek.veinserver.service.FeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.security.GeneralSecurityException;

@Controller
@RequestMapping("feedBack")
public class FeedBackController {


    @Autowired
    private FeedBackService feedBackService;


    /**
     * 创建人：wyx
     * 描述：意见反馈
     * @param files
     * @param ideaType
     * @param ideaInfo
     * @param e_mail
     * @return
     */
    @RequestMapping(value = "/feedbackMessage" , method = RequestMethod.POST)
    @ResponseBody
    public JSONObject feedbackMessage(MultipartFile[] files, Integer ideaType, String ideaInfo, String e_mail) throws MessagingException, GeneralSecurityException {
        JSONObject jsonObject = feedBackService.feedbackMessage(ideaType, ideaInfo, e_mail, files);
        return jsonObject;
    }
}
