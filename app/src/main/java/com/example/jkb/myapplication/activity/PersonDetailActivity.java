package com.example.jkb.myapplication.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jkb.myapplication.MyApplication;
import com.example.jkb.myapplication.MyViewModel;
import com.example.jkb.myapplication.Person;
import com.example.jkb.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jkb on 18/4/3.
 */

public class PersonDetailActivity extends BaseActivity {
    @BindView(R.id.textView)
    EditText textView;
    @BindView(R.id.textView2)
    EditText textView2;
    @BindView(R.id.textView3)
    EditText textView3;

    MyViewModel myViewModel;

    int id;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_person);
        ButterKnife.bind(this);
        id = getIntent().getIntExtra("id",0);
        init(id);
    }

    void init(int id){
        myViewModel = new MyViewModel(((MyApplication)getApplication()).personRepository);
        myViewModel.init(id);
//        myViewModel.getPersonMutableLiveData().observe(this, new Observer<Person>() {
//            @Override
//            public void onChanged(@Nullable Person person) {
//                if (person!=null){
//                    textView.setText(person.getName());
//                    textView2.setText(person.getAddress());
//                    textView3.setText(person.getPhone());
//                }
//            }
//        });

        myViewModel.getPersonMutableLiveData().observe(this, personResource -> {
            if (personResource!=null){
                switch (personResource.status){
                    case ERROR:
                        Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
                        break;
                    case LOADING:
                        Toast.makeText(getApplicationContext(),"LOADING",Toast.LENGTH_SHORT).show();
                        break;
                    case SUCCESS:
                        textView.setText(personResource.data.getName());
                        textView2.setText(personResource.data.getAddress());
                        textView3.setText(personResource.data.getPhone());
                        break;
                }

            }
        });
    }

    @OnClick({R.id.button3, R.id.button4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button3:
                Person person = new Person();
                person.setId(id);
                person.setName(textView.getText().toString());
                person.setAddress(textView2.getText().toString());
                person.setPhone(textView3.getText().toString());
                myViewModel.savePerson(person);
                finish();
                break;
            case R.id.button4:
                myViewModel.removePerson(id);
                finish();
                break;
        }
    }
}
