package com.huantek.veinserver.model;

import lombok.Data;

/**
 * 创建人：wyx
 * 类说明:作用于存放返回信息;
 */
@Data
public class JsonModel {
    private boolean success;
    private int errorCode;
    private Object dataObject;
    private String errorMessage;
    private long currentTime;
}
