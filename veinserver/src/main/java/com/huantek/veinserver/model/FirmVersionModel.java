package com.huantek.veinserver.model;


import lombok.Data;

@Data
public class FirmVersionModel {

    private String firmVersionName;
    private float firmVersionCode;
    private String firmVersionInfo;
    private String firmVersionUrl;
    private long uploadTime;
}
