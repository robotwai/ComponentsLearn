package com.example.jkb.myapplication;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

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
 * Created by jkb on 18/3/5.
 */

public class ListActivity extends AppCompatActivity {
    @BindView(R.id.lv)
    ListView lv;
    MyAdapter adapter;
    PersonListViewModel viewModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_list);
        ButterKnife.bind(this);
//        DaggerListComponent.builder()
        viewModel = new PersonListViewModel(((MyApplication)getApplication()).personRepository);

        adapter = new MyAdapter(this);


        lv.setAdapter(adapter);
        viewModel.getListLiveData().observe(this, new Observer<List<Person>>() {
            @Override
            public void onChanged(@Nullable List<Person> people) {
                Log.i("lzz",people.size()+"change");
                adapter.setData(people);
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                viewModel.remove((int)id);
                return false;
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListActivity.this,PersonDetailActivity.class);
                intent.putExtra("id",(int)id);
                startActivity(intent);
            }
        });
    }


    @OnClick(R.id.btn_add)
    public void onViewClicked() {
        startActivity(new Intent(this,PersonDetailActivity.class));
    }

    class MyAdapter extends BaseAdapter {

        private Context context;

        private List<Person> listLiveData = new ArrayList<>();

        public MyAdapter(Context context) {
            this.context = context;
        }

        public void setData(List<Person> list) {
            listLiveData = list;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return listLiveData.size();
        }

        @Override
        public Object getItem(int position) {
            return listLiveData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return listLiveData.get(position).getId();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(context).inflate(
                        R.layout.ad_person, null);
                holder.textName = convertView.findViewById(R.id.textView7);
                holder.textAddress = convertView.findViewById(R.id.textView8);
                holder.textPhone = (TextView) convertView.findViewById(R.id.textView9);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.textName.setText(listLiveData.get(position).getName());

            holder.textAddress.setText(listLiveData.get(position).getAddress());
            holder.textPhone.setText(listLiveData.get(position).getPhone() + "");
            return convertView;
        }


        class ViewHolder {
            private TextView textName;
            private TextView textAddress;
            private TextView textPhone;

        }
    }
}
