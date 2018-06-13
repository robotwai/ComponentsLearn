package com.example.jkb.myapplication;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

/**
 * Created by jkb on 18/5/10.
 */

public class FeedModel extends ViewModel {


    private LiveData<Resource<List<Micropost>>> listLiveData;
    private MicropostRepository repository;

    public FeedModel(MicropostRepository repository) {
        this.repository = repository;
        listLiveData = repository.getMicropost();
    }

    public LiveData<Resource<List<Micropost>>> getListLiveData(){
        return listLiveData;
    }

    public void refresh(){
        repository.networkBoundResource.page=1;
        repository.networkBoundResource.start();
    }

    public void getNextPage(){
        repository.networkBoundResource.setHasNext(true);
        repository.networkBoundResource.start();
    }

    public void dot(int id,int type){
        repository.dot(id,type);
    }
}
