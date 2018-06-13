package com.example.jkb.myapplication;

import android.arch.lifecycle.LiveData;

import com.example.jkb.myapplication.data.BaseBean;
import com.example.jkb.myapplication.data.BaseResponse;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by jkb on 18/3/28.
 */

public interface DemoService {
    String url = "http://192.168.1.112:3000/";

    @GET("users/1")
    Call<ResponseBody> getDoctorInfo();
    @GET("static_pages/json.json")
    Call<ResponseBody>  getNotify();
    @GET("people/get_data.json")
    LiveData<ApiResponse<List<Person>>>  getPeople();

    @POST("people/save_data.json")
    Call<BaseResponse<Person>> savePeople(@QueryMap Map<String, String> options);

    @POST("people/remove.json")
    Call<BaseResponse> deletePeople(@Query("id") int id);

    @GET("people/get_one_data.json")
    Call<BaseResponse<Person>>  getPeople(@Query("id") int id);

    @POST("people/update_data.json")
    Call<BaseResponse<Person>> updatePeople(@QueryMap Map<String, String> options);

    @GET("people/get_one_data.json")
    LiveData<ApiResponse<Person>>  getPerson(@Query("id") int id);

    @POST("applogin")
    ApiResponse<BaseResponse> login(@QueryMap Map<String, String> options);

    @FormUrlEncoded
    @POST("app/loggin")
    Call<User> norlogin(@FieldMap Map<String, String> options);

    @Multipart
    @POST("app/register")
    Call<BaseBean> signup(@Part MultipartBody.Part file, @QueryMap Map<String, String> options);


    @GET("app/feed")
    LiveData<ApiResponse<List<Micropost>>>  getUserMicropost(@Query("page") int page);

    @Multipart
    @POST("app/seedmicropost")
    Call<BaseBean> send(@Part MultipartBody.Part file,@QueryMap Map<String, String> options);

    @GET("app/getMicropost")
    Call<Micropost> getMicropost(@Query("id")int id);

    @POST("app/dot")
    Call<Micropost> dot(@Query("micropost_id")int id);

    @POST("app/dotDestroy")
    Call<Micropost> dotDestory(@Query("id")int id);
}
