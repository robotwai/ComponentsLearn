package com.example.jkb.myapplication.utils;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.annotation.Nullable;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jkb on 18/5/16.
 */

public class ExtraGsonConverterFactory extends Converter.Factory {
    private final Gson gson;

    public static ExtraGsonConverterFactory create() {
        return create(new Gson());
    }

    public static ExtraGsonConverterFactory create(Gson gson) {
        if (gson == null) throw new NullPointerException("gson == null");
        return new ExtraGsonConverterFactory(gson);
    }

    private ExtraGsonConverterFactory(Gson gson) {
        this.gson = gson;
    }
    @Nullable
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new ExtraGsonResponseBodyConverter<>(gson, adapter);

    }
}
