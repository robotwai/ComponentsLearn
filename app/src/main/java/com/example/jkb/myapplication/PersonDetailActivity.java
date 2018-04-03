package com.example.jkb.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jkb on 18/4/3.
 */

public class PersonDetailActivity extends AppCompatActivity {
    @BindView(R.id.textView)
    EditText textView;
    @BindView(R.id.textView2)
    EditText textView2;
    @BindView(R.id.textView3)
    EditText textView3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_person);
        ButterKnife.bind(this);
    }

    void init(){

    }

    @OnClick({R.id.button3, R.id.button4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button3:
                break;
            case R.id.button4:
                break;
        }
    }
}
