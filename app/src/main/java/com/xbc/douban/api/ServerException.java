package com.xbc.douban.api;

/**
 * Created by xiaobocui on 2017/10/24.
 */

public class ServerException extends RuntimeException {

    public int code;
    public String message;

//    public ServerException() {
//    }

    public ServerException(int code) {
        this.code = code;
    }
}
