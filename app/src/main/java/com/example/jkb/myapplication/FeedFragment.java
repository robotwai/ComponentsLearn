package com.example.jkb.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jkb on 18/5/9.
 */

public class FeedFragment extends BaseFragment {
    int id;
    @BindView(R.id.lv)
    ListView lv;
    Unbinder unbinder;
    MicropostAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f_feed, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        id = getArguments().getInt(ID);
        LogUtils.log("onActivityCreated"+id);
        refresh();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new MicropostAdapter(getContext());
        lv.setAdapter(adapter);

    }


    void refresh() {
        ((MyApplication) getActivity().getApplication()).personRepository.webService.getUserMicropost(id, 1)
                .enqueue(new Callback<List<Micropost>>() {
                    @Override
                    public void onResponse(Call<List<Micropost>> call, Response<List<Micropost>> response) {
                        if (response!=null&&response.body()!=null){
                            adapter.setData(response.body());
                        }else {

                        }
                    }

                    @Override
                    public void onFailure(Call<List<Micropost>> call, Throwable t) {

                    }
                });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
