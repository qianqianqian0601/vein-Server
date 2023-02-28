package com.huantek.veinserver.model;


import lombok.Data;

@Data
public class ServerVersionModel {

    private String serverName;
    private float serverSize;
    private float serverCode;
    private String serverInfo;
    private String serverUrl;
    private long uploadTime;
}
