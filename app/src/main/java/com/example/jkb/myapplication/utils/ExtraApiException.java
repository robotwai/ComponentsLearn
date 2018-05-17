package com.example.jkb.myapplication.utils;

import com.example.jkb.myapplication.LogUtils;

import java.io.IOException;

/**
 * Created by jkb on 18/5/16.
 */

public class ExtraApiException extends IOException {
    int status;
    String message;

    public ExtraApiException(int status,String message) {
        super(message);
        this.status = status;
        this.message = message;
        LogUtils.log(message+status);
    }


}
