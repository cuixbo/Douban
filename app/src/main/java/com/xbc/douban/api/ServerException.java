package com.xbc.douban.api;

/**
 * Created by xiaobocui on 2017/10/24.
 */

public class ServerException extends Exception {
    public int code;
    public String message;
}
