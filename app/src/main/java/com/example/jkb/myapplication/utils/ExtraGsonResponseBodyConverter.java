package com.example.jkb.myapplication.utils;

import android.text.TextUtils;

import com.example.jkb.myapplication.data.AccountManager;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Converter;

import static okhttp3.internal.Util.UTF_8;

/**
 * Created by jkb on 18/5/16.
 */
//作者：AFAP
//        链接：https://www.jianshu.com/p/803480ba3c44
//        來源：简书
//        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
final class ExtraGsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    ExtraGsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        try {
            JSONObject response = new JSONObject(value.string());


            // 结果状态不对的，统一抛出异常，进入Subscriber的onError回调函数
            if (response.optInt("status") != 0) {
                if (response.optInt("status") == 2){
                    AccountManager.getInstance().exitAccount();
                }
                value.close();
                throw new ExtraApiException(response.optInt("status"), response.optString("data"));
            }

            // 后台返回不统一、不规范，客户端来背锅处理……
            String info = response.optString("data");
            if (TextUtils.isEmpty(info)) {
                info = response.optString("resultList");
            }
            if (TextUtils.isEmpty(info) || TextUtils.equals(info.toLowerCase(), "null")) {
                info = "{}";
            }

            MediaType contentType = value.contentType();
            Charset charset = contentType != null ? contentType.charset(UTF_8) : UTF_8;


            InputStream inputStream = new ByteArrayInputStream(info.getBytes());
            Reader reader = new InputStreamReader(inputStream, charset);
            JsonReader jsonReader = gson.newJsonReader(reader);

            return adapter.read(jsonReader);
        } catch (JSONException e) {
            throw new IOException();
        } finally {
            value.close();
        }
    }
}

