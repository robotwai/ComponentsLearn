package com.example.jkb.myapplication;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mModel = new MyViewModel(new PersonRepository(PersonDatabase.getInstance(getApplicationContext()).personDao()));

        // Create the observer which updates the UI.
//        final Observer<String> nameObserver = new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable final String newName) {
//                // Update the UI, in this case, a TextView.
//                sample_text.setText(newName);
//            }
//        };

//        Executors.newSingleThreadExecutor().execute(new Runnable() {
//            @Override
//            public void run() {
//
//                Person person = new Person();
//                person.setUid(1);
//                person.setName("lzzz");
//                person.setAddress("ssdgsdg");
//                person.setPhone(123);
//                mModel.savePerson(person);
//            }
//        });

        mModel.init(1);

        final Observer<Person> personObserver = new Observer<Person>() {
            @Override
            public void onChanged(@Nullable Person person) {
                textView4.setText(person.getName());
                textView5.setText(person.getAddress());
                textView6.setText(person.getPhone() + "");
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

    @OnClick(R.id.button)
    public void onViewClicked() {

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {

                Person person = new Person();
                person.setUid(1);
                person.setName(editText4.getText().toString());
                person.setAddress(editText5.getText().toString());
                person.setPhone(Integer.parseInt(editText6.getText().toString()));
                mModel.savePerson(person);
            }
        });

    }
}
