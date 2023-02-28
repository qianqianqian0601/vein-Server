package com.huantek.veinserver.service;

import org.springframework.web.multipart.MultipartFile;

public interface PhoneVersionService {


    String PhoneVersionUP(MultipartFile PhoneFile, String PhoneVersionName, float PhoneVersionCode, String PhoneVersionInfo);


    String PhoneVersionDownLatest();
}
