package com.example.jkb.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jkb.myapplication.activity.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;

/**
 * Created by jkb on 18/5/9.
 */

public class FeedFragment extends BaseFragment {
    int id;
    @BindView(R.id.lv)
    ListView lv;
    Unbinder unbinder;
    MicropostAdapter adapter;
    @BindView(R.id.sw)
    SwipeRefreshLayout sw;

    private boolean hasMore;

    FeedModel feedModel;

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
        LogUtils.log("onActivityCreated" + id);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sw.setProgressBackgroundColorSchemeResource(android.R.color.white);
        sw.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light, android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
        sw.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        sw.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                feedModel.refresh();
            }
        });
        adapter = new MicropostAdapter(getContext());
        lv.setAdapter(adapter);
        lv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                Log.i("firstVisibleItem", firstVisibleItem + "");
                Log.i("visibleItemCount", visibleItemCount + "");
                Log.i("totalItemCount", totalItemCount + "");
                if (firstVisibleItem + visibleItemCount >= totalItemCount && hasMore) {
                    feedModel.getNextPage();
                    hasMore= false;
                }
            }
        });
        feedModel = new FeedModel(((MyApplication) getActivity().getApplication()).micropostRepository);
        feedModel.getListLiveData().observe(this, feed -> {
            if (feed != null) {
                switch (feed.status) {
                    case ERROR:
                        Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
                        sw.setRefreshing(false);
                        adapter.clearData();
                        hasMore =false;
                        LogUtils.log(feed.data.get(0).toString());
                        adapter.setData(feed.data);
                        break;
                    case LOADING:
//                        Toast.makeText(getContext(), "LOADING", Toast.LENGTH_SHORT).show();
                        break;
                    case SUCCESS:
                        sw.setRefreshing(false);
                        LogUtils.log(feed.data.size()+"size");
                        if (feed.data!=null&&feed.data.size()%30==0){
                            hasMore=true;
                        }else {
                            hasMore =false;
                        }
                        adapter.clearData();
                        adapter.setData(feed.data);
                        break;

                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            feedModel.refresh();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
