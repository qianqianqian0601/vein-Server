package com.huantek.veinserver.service;

import org.springframework.web.multipart.MultipartFile;

public interface SoftwareServerVersionService  {


    String ServerVersionUP(MultipartFile serverFile, String serverVersionName, float serverVersionCode, String serverVersionInfo);


    String ServerVersionDownLatest();
}
