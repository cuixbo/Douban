package com.xbc.douban.api;

public class ServerException extends RuntimeException {

    public int code;
    public String message;

//    public ServerException() {
//    }

    public ServerException(int code) {
        this.code = code;
    }
}
