package com.huantek.veinserver.model;


import lombok.Data;

@Data
public class SoftwareVersionModel {

    private String softwareName;
    private float softwareSize;
    private float softwareCode;
    private String softwareInfo;
    private String softwareUrl;
    private long uploadTime;
}
