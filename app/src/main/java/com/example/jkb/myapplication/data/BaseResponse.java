package com.example.jkb.myapplication.data;

import java.io.Serializable;

/**
 * Created by lz on 2017/3/22.
 */

public class BaseResponse<T> implements Serializable {


    T data;
    int status;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
