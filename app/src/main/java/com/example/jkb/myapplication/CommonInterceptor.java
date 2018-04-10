package com.example.jkb.myapplication;


import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lz on 2017/4/25.
 */

public class CommonInterceptor implements Interceptor {


    public CommonInterceptor() {

    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request oldRequest = chain.request();

        // 添加新的参数
        HttpUrl.Builder authorizedUrlBuilder = oldRequest.url()
                .newBuilder()
                .scheme(oldRequest.url().scheme())
                .host(oldRequest.url().host());



        // 新的请求
        Request newRequest = oldRequest.newBuilder()
                .method(oldRequest.method(), oldRequest.body())
                .url(authorizedUrlBuilder.build())
                .build();

        return chain.proceed(newRequest);
    }
}
