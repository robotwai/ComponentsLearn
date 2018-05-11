package com.example.jkb.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
 * Created by jkb on 18/5/11.
 */

public class AddMicropostActivity extends BaseActivity {
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.iv_select)
    ImageView ivSelect;
    @BindView(R.id.iv_show)
    ImageView ivShow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_add_micropost);
        ButterKnife.bind(this);
        etContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etContent.getText()!=null){
                    tvNum.setText(etContent.getText().toString().length()+"/140");
                }else {
                    tvNum.setText("0/140");
                }
            }
        });
    }

    @OnClick({R.id.tv_cancel, R.id.tv_send, R.id.iv_select, R.id.iv_show})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                finish();
                break;
            case R.id.tv_send:
                try {
                    send();
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case R.id.iv_select:
                changeIcon();
                break;
            case R.id.iv_show:
                changeIcon();
                break;
        }
    }

    void changeIcon(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");//相片类型
        startActivityForResult(intent, 1);
    }

    private void send() throws Exception {
        if (etContent.getText().toString().isEmpty()){
            Toast.makeText(AddMicropostActivity.this,"请添加一段文字",Toast.LENGTH_SHORT).show();
            return;
        }
        if (imgPath==null){
            Toast.makeText(AddMicropostActivity.this,"至少添加一张图片",Toast.LENGTH_SHORT).show();
            return;
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("content", etContent.getText().toString());

        File file = new File(imgPath);
        //构建requestbody
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        //将resquestbody封装为MultipartBody.Part对象
        MultipartBody.Part body = MultipartBody.Part.createFormData("picture", file.getName(), requestFile);
        ((MyApplication) getApplication()).personRepository.webService.send(body,map)
                .enqueue(new Callback<BaseResponse>() {
                    @Override
                    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                        if (response.body()!=null&&response.body().getStatus()==0){
                            Toast.makeText(AddMicropostActivity.this,"注册成功，请检查邮箱激活，并登陆",Toast.LENGTH_SHORT).show();
                            setResult(RESULT_OK);
                            finish();
                        }else {
                            Toast.makeText(AddMicropostActivity.this,response.body().getData().toString(),Toast.LENGTH_SHORT).show();
                        }


                    }

                    @Override
                    public void onFailure(Call<BaseResponse> call, Throwable t) {
                        Toast.makeText(AddMicropostActivity.this,"请检查网络",Toast.LENGTH_SHORT).show();
                    }
                });
    }


    String imgPath;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode ==1 &&resultCode==RESULT_OK){
            ivShow.setImageURI(data.getData());
            ivSelect.setVisibility(View.GONE);
            ivShow.setVisibility(View.VISIBLE);
            try {
                imgPath = PictureUtil.compressImg(AddMicropostActivity.this,data.getData(),"micropost"+System.currentTimeMillis());
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }
}
