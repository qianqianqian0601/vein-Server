package com.huantek.veinserver.ossUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OSSClientConstants {


    private static String getTime(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(date);
        return format;
    }


    public final static String ENDPOINT = "oss-cn-shenzhen.aliyuncs.com";//阿里云网域
    public final static String ACCESS_KEY_ID = "LTAI4FtnpgWmRGKVpYCi9fXc";//AKC ID
    public final static String ACCESS_KEY_SECRET = "ftXgdWWaZEKaZDpdR6yFlLUK1seNVH";//AKC SECRET
    public final static String BACKET_NAME = "vein-server";//库名
    public final static String FIRM_FOLDER = "Vein/Firmware/";//固件文件夹
    public final static String HANDY2_FOLDER = "Vein/Handy2/";//handy固件文件夹
    public static final String SOFTWARE_FOLDER = "Vein/software/";//软件文件夹
    public static final String PHONE_FOLDER = "Vein/phone/";//手机助手软件文件夹
    public static final String SERVER_FOLDER = "Vein/server/";//软件文件夹
    public static final String FEEDBACK_IMG = "Vein/feedBack_IMG/";//意见反馈文件夹

}
