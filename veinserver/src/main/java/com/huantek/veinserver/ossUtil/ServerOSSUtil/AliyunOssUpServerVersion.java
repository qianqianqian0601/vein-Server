package com.huantek.veinserver.ossUtil.ServerOSSUtil;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.huantek.veinserver.ossUtil.OSSClientConstants;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class AliyunOssUpServerVersion {


    //阿里云API的内或外网域名
    private static String ENDPOINT;
    //阿里云API的密钥Access Key ID
    private static String ACCESS_KEY_ID;
    //阿里云API的密钥Access Key Secret
    private static String ACCESS_KEY_SECRET;
    //阿里云API的bucket名称
    private static String BACKET_NAME;
    //阿里云API的文件夹名称
    private static String SERVER_FOLDER;




    //初始化属性
    static {
        ENDPOINT = OSSClientConstants.ENDPOINT;
        ACCESS_KEY_ID = OSSClientConstants.ACCESS_KEY_ID;
        ACCESS_KEY_SECRET = OSSClientConstants.ACCESS_KEY_SECRET;
        BACKET_NAME = OSSClientConstants.BACKET_NAME;
        SERVER_FOLDER = OSSClientConstants.SERVER_FOLDER;
    }

    public static OSSClient getOSSClient() {
        return new OSSClient(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
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
            ossClient.putObject(BACKET_NAME, SERVER_FOLDER + fileName, instream, objectMetadata);
        } catch (IOException e) {

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

    public static String save(MultipartFile multipartFile, OSSClient ossClient){
        // 获取图片名字
        String fileName = multipartFile.getOriginalFilename();
        //防止名字冲突覆盖原有文件
        try {
            InputStream inputStream = multipartFile.getInputStream();
            AliyunOssUpServerVersion.uploadFile2OSSTest(inputStream, fileName,ossClient);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String  urlName = fileName.substring(fileName.lastIndexOf("\\")+1,fileName.length());
        return "http://" + BACKET_NAME+"."+ENDPOINT+ File.separator + SERVER_FOLDER +urlName;
    }
}
