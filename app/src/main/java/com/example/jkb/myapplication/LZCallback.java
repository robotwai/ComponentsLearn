package com.example.jkb.myapplication;

import com.example.jkb.myapplication.data.BaseResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jkb on 18/4/10.
 */

public abstract class LZCallback<T> implements Callback<BaseResponse<T>> {

    int STATUS_SUCCESS = 1;
    int STATUS_FAILURE = 0;
    @Override
    public void onResponse(Call<BaseResponse<T>> call, Response<BaseResponse<T>> response) {
        if (response.body() != null) {
            if (response.body().getStatus() == STATUS_SUCCESS) {
                LogUtils.log("setData");
                setDate(response.body().getData());
            } else if (response.body().getStatus() == STATUS_FAILURE){
                setError();
            }

        } else {
           setError();

        }
    }

    @Override
    public void onFailure(Call<BaseResponse<T>> call, Throwable t) {
        setError();
    }

    abstract void setDate(T t);

    abstract void setError();
}
