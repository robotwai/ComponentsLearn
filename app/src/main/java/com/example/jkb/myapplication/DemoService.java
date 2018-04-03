package com.example.jkb.myapplication;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by jkb on 18/3/28.
 */

public interface DemoService {
    @GET("users/1")
    Call<ResponseBody> getDoctorInfo();
    @GET("static_pages/json.json")
    Call<ResponseBody>  getNotify();
    @GET("people.json")
    Call<ResponseBody>  getPeople();

    @POST("people/get_data.json")
    Call<ResponseBody> savePeople(@Query("name") String name,@Query("address") String address,
                                  @Query("phone") String phone);

    @POST("people/remove.json")
    Call<ResponseBody> deletePeople(@Query("id") int id);

    @GET("people/get_one_data.json")
    Call<ResponseBody>  getPeople(@Query("id") int id);
}
