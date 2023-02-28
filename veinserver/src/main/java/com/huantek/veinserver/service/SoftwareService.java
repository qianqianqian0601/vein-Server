package com.huantek.veinserver.service;


import org.springframework.web.multipart.MultipartFile;

public interface SoftwareService {

    String softwareVersionUP(MultipartFile softwareFile, String softwareVersionName, float softwareVersionCode, String softwareVersionInfo);

    String softwareVersionDownLatest();
}
