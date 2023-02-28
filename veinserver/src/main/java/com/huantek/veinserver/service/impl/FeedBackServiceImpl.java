package com.huantek.veinserver.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSSClient;
import com.huantek.veinserver.dao.FeedBackDao;
import com.huantek.veinserver.ossUtil.feedBackUtil.AliyunOssUpFeedbackIMG;
import com.huantek.veinserver.ossUtil.feedBackUtil.EmailUtil;
import com.huantek.veinserver.service.FeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FeedBackServiceImpl implements FeedBackService {

    @Autowired
    private FeedBackDao feedBackDao;

    @Override
    public JSONObject feedbackMessage(Integer ideaType, String ideaInfo, String e_mail, MultipartFile[] files) throws GeneralSecurityException, MessagingException {
        JSONObject jsonObject = new JSONObject();
        if (ideaType==null||ideaType.equals(0)){
            jsonObject.put("success",false);
            jsonObject.put("errorMessage","值异常");
            jsonObject.put("errorCode",1004);
            jsonObject.put("dataObject",null);
            jsonObject.put("currentTime",System.currentTimeMillis());
            return jsonObject;
        }
        if (ideaType==null){
            jsonObject.put("success",false);
            jsonObject.put("errorMessage","值异常");
            jsonObject.put("errorCode",1004);
            jsonObject.put("dataObject",null);
            jsonObject.put("currentTime",System.currentTimeMillis());
            return jsonObject;
        }
        if (e_mail==null) e_mail="";
        List<String> list = new ArrayList<>();
        if (files!=null||files.length>0){
            OSSClient ossClient = AliyunOssUpFeedbackIMG.getOSSClient();
            for (MultipartFile file : files) {
                if (file.getSize()>30*1024*1024){
                    jsonObject.put("success",false);
                    jsonObject.put("errorMessage","文件过大");
                    jsonObject.put("errorCode",1017);
                    jsonObject.put("dataObject",null);
                    jsonObject.put("currentTime",System.currentTimeMillis());
                    return jsonObject;
                }
                String url = AliyunOssUpFeedbackIMG.saveImg(file, ossClient);
                if (url.equals("格式错误！")){
                    jsonObject.put("success",false);
                    jsonObject.put("errorMessage","请选择正确格式的图片");
                    jsonObject.put("errorCode",1017);
                    jsonObject.put("dataObject",null);
                    jsonObject.put("currentTime",System.currentTimeMillis());
                    return jsonObject;
                }
                list.add(url);
            }
            String urls = "";
            for (int i=0;i<list.size();i++){
                urls += " "+list.get(i);
            }

            StringBuffer sb = new StringBuffer();
            sb.append("<h3>反馈人邮箱: "+e_mail+" </h3>");
            if (ideaType==1) sb.append("<h3>问题类型: <span style='color:red'>遇到BUG</span> </h3>");
            if (ideaType==2) sb.append("<h3>问题类型: <span style='color:red'>Sprint使用问题咨询</span> </h3>");
            if (ideaType==3) sb.append("<h3>问题类型: <span style='color:red'>对Sprint的建议</span> </h3>");
            sb.append("<h3>问题描述: </h3><div style='margin-bottom: 40px;color: gray;text-indent: 2em;'>"+ideaInfo+"</div>");
            for (int i=0;i<list.size();i++){
                sb.append("<div style='justify-content: center;width:500px;height:500px;margin-bottom: 44px; display: flex;align-items: center;border-width: 1px;border-color: gray;border-style: solid;'><img src='"+list.get(i)+"' style='max-height: 100%;max-width: 100%;'></div>");
            }
            EmailUtil.sendMessageMail(sb.toString());

            try {
                feedBackDao.feedbackMessageAndIMG(ideaType,ideaInfo,e_mail,urls);
                jsonObject.put("success",true);
                jsonObject.put("errorMessage","意见反馈已提交");
                jsonObject.put("errorCode",0);
                jsonObject.put("dataObject",null);
                jsonObject.put("currentTime",System.currentTimeMillis());
                return jsonObject;
            }catch (Exception e){
                e.printStackTrace();
                jsonObject.put("success",false);
                jsonObject.put("errorMessage","服务异常");
                jsonObject.put("errorCode",1002);
                jsonObject.put("dataObject",null);
                jsonObject.put("currentTime",System.currentTimeMillis());
                return jsonObject;
            }
        }else {
            try {
                feedBackDao.feedbackMessage(ideaType,ideaInfo,e_mail);
                jsonObject.put("success",true);
                jsonObject.put("errorMessage","意见反馈已提交");
                jsonObject.put("errorCode",0);
                jsonObject.put("dataObject",null);
                jsonObject.put("currentTime",System.currentTimeMillis());
                return jsonObject;
            }catch (Exception e){
                e.printStackTrace();
                jsonObject.put("success",false);
                jsonObject.put("errorMessage","服务异常");
                jsonObject.put("errorCode",1002);
                jsonObject.put("dataObject",null);
                jsonObject.put("currentTime",System.currentTimeMillis());
                return jsonObject;
            }
        }
    }
}
