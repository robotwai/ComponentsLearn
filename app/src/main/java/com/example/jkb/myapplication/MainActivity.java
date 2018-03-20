package com.example.jkb.myapplication;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.Executors;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    @Inject
    PersonRepository repository;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        DaggerPersonComponent.builder().build().inject(this);
        DaggerPersonComponent.builder().personMoudle(new PersonMoudle(this)).build().inject(this);
        mModel = new MyViewModel(repository);


        mModel.init(5);

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

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
//        mModel.getCurrentName().observe(this, nameObserver);
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String anotherName = "John Doe";
//                mModel.getCurrentName().postValue(anotherName);
//            }
//        });
    }


    @OnClick({R.id.button, R.id.button2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button:
                Person person = new Person();
                person.setName(editText4.getText().toString());
                person.setAddress(editText5.getText().toString());
                person.setPhone(Integer.parseInt(editText6.getText().toString()));
                mModel.savePerson(person);
                break;
            case R.id.button2:
                startActivity(new Intent(this,ListActivity.class));
                break;
        }
    }
}
