package com.huantek.veinserver.ossUtil.feedBackUtil;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.huantek.veinserver.ossUtil.OSSClientConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author jack
 */
@Slf4j
public class AliyunOssUpFeedbackIMG {

    //阿里云API的内或外网域名
    private static String ENDPOINT;
    //阿里云API的密钥Access Key ID
    private static String ACCESS_KEY_ID;
    //阿里云API的密钥Access Key Secret
    private static String ACCESS_KEY_SECRET;
    //阿里云API的bucket名称
    private static String BACKET_NAME;
    //阿里云API的文件夹名称
    private static String FEEDBACK_IMG;

    //初始化属性
    static {
        ENDPOINT = OSSClientConstants.ENDPOINT;
        ACCESS_KEY_ID = OSSClientConstants.ACCESS_KEY_ID;
        ACCESS_KEY_SECRET = OSSClientConstants.ACCESS_KEY_SECRET;
        BACKET_NAME = OSSClientConstants.BACKET_NAME;
        FEEDBACK_IMG = OSSClientConstants.FEEDBACK_IMG;
    }

    public static OSSClient getOSSClient() {
        return new OSSClient(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
    }

    private static boolean getContentType(String fileName) {
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
        if (fileExtension.equals(".jpg") || fileExtension.equals(".png") || fileExtension.equals(".jpeg")){
            return true;
        }
        return false;
    }

    /**
     * 上传到OSS服务器  如果同名文件会覆盖服务器上的
     *
     * @param instream 文件流
     * @param fileName 文件名称 包括后缀名
     * @return 出错返回"" ,唯一MD5数字签名
     */
    private static void uploadFile2OSSTest(InputStream instream, String fileName,
                                           OSSClient ossClient) {
        try {
            //创建上传Object的Metadata
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(instream.available());
            objectMetadata.setCacheControl("no-cache");
            objectMetadata.setHeader("Pragma", "no-cache");
            objectMetadata.setContentDisposition("inline;filename=" + fileName);
            //上传文件
            ossClient.putObject(BACKET_NAME, FEEDBACK_IMG + fileName, instream, objectMetadata);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            try {
                if (instream != null) {
                    instream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //上传一张图片
    public static String saveImg(MultipartFile multipartFile, OSSClient ossClient){
        //限制图片大小
        // 获取图片名字
        String fileName = multipartFile.getOriginalFilename();
        boolean flag = AliyunOssUpFeedbackIMG.getContentType(fileName);
        if (!flag){
            return "格式错误！";
        }
        //防止名字冲突覆盖原有图片
        String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        fileName = fileName.replace(" ","-").toLowerCase();
        fileName=uuid+fileName;
        try {
            InputStream inputStream = multipartFile.getInputStream();
            AliyunOssUpFeedbackIMG.uploadFile2OSSTest(inputStream, fileName,ossClient);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String  urlName = fileName.substring(fileName.lastIndexOf("\\")+1);
        return "https://" + BACKET_NAME+"."+ENDPOINT+ File.separator + FEEDBACK_IMG +urlName;
    }

}
