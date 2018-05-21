package com.example.jkb.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.jkb.myapplication.MyApplication;
import com.example.jkb.myapplication.R;
import com.example.jkb.myapplication.data.BaseBean;
import com.example.jkb.myapplication.data.BaseResponse;
import com.example.jkb.myapplication.utils.PictureUtil;

import java.io.File;
import java.net.URISyntaxException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jkb on 18/5/7.
 */

public class RegisterActivity extends BaseActivity {
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_password_confirm)
    EditText etPasswordConfirm;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.iv_icon)
    ImageView ivIcon;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_register);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.button5, R.id.iv_icon})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button5:
                try {
                    register();
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case R.id.iv_icon:
                changeIcon();
                break;
        }
    }

    private void register() throws Exception {
        HashMap<String, String> map = new HashMap<>();
        map.put("email", etEmail.getText().toString());
        map.put("name",etName.getText().toString());
        map.put("password_confirmation",etPasswordConfirm.getText().toString());
        map.put("password", etPassword.getText().toString());
        File file = new File(compressImg);
        //构建requestbody
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        //将resquestbody封装为MultipartBody.Part对象
        MultipartBody.Part body = MultipartBody.Part.createFormData("icon", file.getName(), requestFile);
        ((MyApplication) getApplication()).personRepository.webService.signup(body,map)
                .enqueue(new Callback<BaseBean>() {
                    @Override
                    public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                        if (response.body()!=null){
                            if (response.body()!=null){
                                Toast.makeText(RegisterActivity.this,"注册成功，请前往邮箱检查激活邮件",Toast.LENGTH_SHORT).show();
                                finish();
                            }else {
                                Toast.makeText(RegisterActivity.this,"注册失败",Toast.LENGTH_SHORT).show();
                            }

                        }else {
                            Toast.makeText(RegisterActivity.this,"注册失败",Toast.LENGTH_SHORT).show();
                        }


                    }

                    @Override
                    public void onFailure(Call<BaseBean> call, Throwable t) {
                        Toast.makeText(RegisterActivity.this,"请检查网络",Toast.LENGTH_SHORT).show();
                    }
                });
    }


    void changeIcon(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");//相片类型
        startActivityForResult(intent, 1);
    }

    private String compressImg;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode ==1 &&resultCode==RESULT_OK){
            ivIcon.setImageURI(data.getData());
            try {
                compressImg = PictureUtil.compressImg(RegisterActivity.this,data.getData(),"register_icon");
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }



}
