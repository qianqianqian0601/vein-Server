package com.huantek.veinserver.service;


import org.springframework.web.multipart.MultipartFile;

public interface Handy2Service {

    String handy2VersionUP(MultipartFile handy2File, String handy2VersionName, float handy2VersionCode, String handy2VersionInfo);

    String handy2VersionDownLatest();
}
