package com.xbc.douban.api;

/**
 * Created by xiaobocui on 2017/10/24.
 */

public class ApiException extends Exception {
    public int code;
    public String message;

    public ApiException(Throwable throwable, int code) {
        super(throwable);
        this.code = code;
    }
}