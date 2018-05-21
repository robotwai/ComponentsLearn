package com.example.jkb.myapplication;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.jkb.myapplication.data.BaseResponse;
import com.example.jkb.myapplication.data.local.MicropostDao;

import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by jkb on 18/5/10.
 */
@Singleton
public class MicropostRepository {
    MicropostDao micropostDao;
    Executor executor;
    DemoService webService;
    NetworkBoundResource networkBoundResource;
    @Inject
    public MicropostRepository(MicropostDao micropostDao, Executor executor, DemoService webService) {
        this.micropostDao = micropostDao;
        this.executor = executor;
        this.webService = webService;
    }

    public LiveData<Resource<List<Micropost>>> getMicropost(){
        networkBoundResource = new NetworkBoundResource<List<Micropost>,List<Micropost>>(){

            @Override
            protected void saveCallResult(@NonNull List<Micropost> item) {
                Micropost[] p = item.toArray(new Micropost[0]);
                micropostDao.insertAll(p);
            }

            @Override
            protected boolean shouldFetch(@Nullable List<Micropost> data) {
                if (data!=null&&data.size()>0){
                    if (hasNext){
                        return true;
                    }else {
                        return false;
                    }
                }else {
                    return true;
                }
            }

            @NonNull
            @Override
            protected LiveData<List<Micropost>> loadFromDb() {
                return micropostDao.getAll();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<Micropost>>> createCall() {
                return webService.getUserMicropost(page);
            }
        };

        return networkBoundResource.getAsLiveData();
    }
}
