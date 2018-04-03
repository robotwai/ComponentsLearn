package com.example.jkb.myapplication;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by jkb on 18/3/2.
 */

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.textView4)
    TextView textView4;
    @BindView(R.id.textView5)
    TextView textView5;
    @BindView(R.id.textView6)
    TextView textView6;
    @BindView(R.id.editText4)
    EditText editText4;
    @BindView(R.id.editText5)
    EditText editText5;
    @BindView(R.id.editText6)
    EditText editText6;
    private MyViewModel mModel;
//    @Inject
//    PersonRepository repository;

    Retrofit retrofit;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        DaggerPersonComponent.builder().build().inject(this);
//        DaggerPersonComponent.builder().personMoudle(new PersonMoudle(this)).build().inject(this);
        mModel = new MyViewModel(((MyApplication)getApplication()).personRepository);


        mModel.init(1);

        final Observer<Person> personObserver = new Observer<Person>() {
            @Override
            public void onChanged(@Nullable Person person) {
                if (person!=null){
                    textView4.setText(person.getName());
                    textView5.setText(person.getAddress());
                    textView6.setText(person.getPhone() + "");
                }

            }
        };
        mModel.getPersonMutableLiveData().observe(this, personObserver);

        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.45.52:3000/")
                .build();
    }


    @OnClick({R.id.button, R.id.button2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button:
//                Person person = new Person();
//                person.setName(editText4.getText().toString());
//                person.setAddress(editText5.getText().toString());
//                person.setPhone(editText6.getText().toString());
//                mModel.savePerson(person);
                DemoService demoService = retrofit.create(DemoService.class);
                Call<ResponseBody> call = demoService.savePeople(editText4.getText().toString()
                ,editText5.getText().toString(),editText6.getText().toString());
// 用法和OkHttp的call如出一辙,
// 不同的是如果是Android系统回调方法执行在主线程
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            System.out.println(response.body().string());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        t.printStackTrace();
                    }
                });

                break;
            case R.id.button2:
                startActivity(new Intent(this,ListActivity.class));
//                testNet();
                break;
        }
    }

    void testNet(){
        DemoService demoService = retrofit.create(DemoService.class);
        Call<ResponseBody> call = demoService.getNotify();
// 用法和OkHttp的call如出一辙,
// 不同的是如果是Android系统回调方法执行在主线程
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    System.out.println(response.body().string());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }
}
