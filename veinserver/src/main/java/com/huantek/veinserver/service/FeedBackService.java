package com.huantek.veinserver.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.security.GeneralSecurityException;

public interface FeedBackService {

    JSONObject feedbackMessage(Integer ideaType, String ideaInfo, String e_mail, MultipartFile[] files) throws GeneralSecurityException, MessagingException;

}
