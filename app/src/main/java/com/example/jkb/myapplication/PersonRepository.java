package com.example.jkb.myapplication;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.jkb.myapplication.data.BaseResponse;
import com.example.jkb.myapplication.data.PersonDataResource;
import com.example.jkb.myapplication.data.local.PersonDao;
import com.example.jkb.myapplication.data.local.PersonLocalDataResource;
import com.example.jkb.myapplication.data.network.PersonNetDataResource;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by lz on 18-3-5.
 */
@Singleton
public class PersonRepository {
    public PersonDao personDao;
    public Executor executor;
    public DemoService webService;
    @Inject
    public PersonRepository( PersonDao personDao, Executor executor,DemoService webService) {
        this.personDao = personDao;
        this.executor = executor;
        this.webService = webService;
    }

    public LiveData<Resource<Person>> getPerson(int id){
//        refreshPerson(id);
        // return a LiveData directly from the database.
//        return personDao.getPerson(id);


//        return new NetworkBoundResource<Person,Person>() {
//            @Override
//            protected void saveCallResult(@NonNull Person item) {
//                personDao.insertAll(item);
//            }
//
//            @Override
//            protected boolean shouldFetch(@Nullable Person data) {
//                return true;
//            }
//
//            @NonNull @Override
//            protected LiveData<Person> loadFromDb() {
//                return personDao.getPerson(id);
//            }
//
//            @NonNull @Override
//            protected LiveData<ApiResponse<Person>> createCall() {
//                return webService.getPerson(id);
//            }
//        }.getAsLiveData();

        return new LiveData<Resource<Person>>(){

        };

    }


    public LiveData<Resource<List<Person>>> getPersons(){
        return new LiveData<Resource<List<Person>>>(){

        };
//        return new NetworkBoundResource<List<Person>,List<Person>>(){
//            @Override
//            protected void saveCallResult(@NonNull List<Person> item) {
//                Person[] p = item.toArray(new Person[0]);
//                personDao.insertAll(p);
//            }
//
//            @Override
//            protected boolean shouldFetch(@Nullable List<Person> data) {
//                if (data!=null&&data.size()>0){
//                    return false;
//                }else {
//                    return true;
//                }
//            }
//
//            @NonNull
//            @Override
//            protected LiveData<List<Person>> loadFromDb() {
//                return personDao.getAll();
//            }
//
//            @NonNull
//            @Override
//            protected LiveData<ApiResponse<List<Person>>> createCall() {
//                return webService.getPeople();
//            }
//        }.getAsLiveData();
    }



    public void savePerson(final Person person){
        HashMap<String, String> map = new HashMap<>();
        map.put("phone", person.getPhone());
        map.put("name", person.getName());
        map.put("address", person.getAddress());
        if (person.getId()!=0){
            map.put("id", person.getId()+"");
            webService.updatePeople(map).enqueue(new Callback<BaseResponse<Person>>() {
                @Override
                public void onResponse(Call<BaseResponse<Person>> call, Response<BaseResponse<Person>> response) {
                    if (response.body()!=null&&response.body().getStatus()==0){
                        executor.execute(()-> personDao.insertAll(response.body().getData()));
                    }
                }

                @Override
                public void onFailure(Call<BaseResponse<Person>> call, Throwable t) {

                }
            });
        }else {
            webService.savePeople(map)
                    .enqueue(new Callback<BaseResponse<Person>>() {
                        @Override
                        public void onResponse(Call<BaseResponse<Person>> call,final Response<BaseResponse<Person>> response) {
                            if (response.body()!=null&&response.body().getStatus()==0){
                                executor.execute(()->
                                    personDao.insertAll(response.body().getData())
                                );
                            }
                        }

                        @Override
                        public void onFailure(Call<BaseResponse<Person>> call, Throwable t) {

                        }
                    });
        }

    }

    public void removePerson(final int id){
        webService.deletePeople(id)
                .enqueue(new Callback<BaseResponse>() {
                    @Override
                    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                        if (response.body()!=null&&response.body().getStatus()==0){
                            executor.execute(()->{
                                Person p =new Person();
                                p.setId(id);
                                personDao.delete(p);
                            });
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseResponse> call, Throwable t) {

                    }
                });
    }

    public void clear(){
        executor.execute(() ->
            personDao.deleteAll()
        );

    }
}
