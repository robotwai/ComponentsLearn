package com.example.jkb.myapplication;

import com.example.jkb.myapplication.data.BaseResponse;

import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by jkb on 18/3/28.
 */

public interface DemoService {
    @GET("users/1")
    Call<ResponseBody> getDoctorInfo();
    @GET("static_pages/json.json")
    Call<ResponseBody>  getNotify();
    @GET("people/get_data.json")
    Call<BaseResponse<List<Person>>>  getPeople();

    @POST("people/save_data.json")
    Call<BaseResponse> savePeople(@QueryMap Map<String, String> options);

    @POST("people/remove.json")
    Call<BaseResponse> deletePeople(@Query("id") int id);

    @GET("people/get_one_data.json")
    Call<BaseResponse<Person>>  getPeople(@Query("id") int id);

    @POST("people/update_data.json")
    Call<BaseResponse> updatePeople(@QueryMap Map<String, String> options);

}
