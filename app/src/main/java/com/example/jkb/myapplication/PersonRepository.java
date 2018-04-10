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
    Retrofit retrofit;
    PersonDao personDao;
    Executor executor;
    DemoService webService;
    @Inject
    public PersonRepository(Retrofit retrofit, PersonDao personDao, Executor executor,DemoService webService) {
        this.retrofit = retrofit;
        this.personDao = personDao;
        this.executor = executor;
        this.webService = webService;
    }

    public LiveData<Resource<Person>> getPerson(int id){
        refreshPerson(id);
        // return a LiveData directly from the database.
//        return personDao.getPerson(id);


        return new NetworkBoundResource<Person,Person>() {
            @Override
            protected void saveCallResult(@NonNull Person item) {
                personDao.insertAll(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable Person data) {
                return true;
            }

            @NonNull @Override
            protected LiveData<Person> loadFromDb() {
                return personDao.getPerson(id);
            }

            @NonNull @Override
            protected LiveData<ApiResponse<Person>> createCall() {
                return webService.getPerson(id);
            }
        }.getAsLiveData();

    }


    public void refreshPerson(final int id){
        if (id ==0){
            return;
        }


        retrofit.create(DemoService.class).getPeople(id).enqueue(new LZCallback<Person>() {
            @Override
            void setDate(final Person person) {
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        personDao.insertAll(person);
                    }
                });
            }

            @Override
            void setError() {

            }
        });

    }

    public LiveData<List<Person>> getPersons(){
        refreshPersons();
        // return a LiveData directly from the database.
        return personDao.getAll();

    }


    public void refreshPersons(){
        retrofit.create(DemoService.class).getPeople().enqueue(
                new Callback<BaseResponse<List<Person>>>() {
                    @Override
                    public void onResponse(Call<BaseResponse<List<Person>>> call,final Response<BaseResponse<List<Person>>> response) {
                        executor.execute(new Runnable() {
                            @Override
                            public void run() {
                                personDao.deleteAll();
                                Person[] p = response.body().getData().toArray(new Person[0]);
                                personDao.insertAll(p);
                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<BaseResponse<List<Person>>> call, Throwable t) {

                    }
                }
        );
    }

    public void savePerson(final Person person){
        HashMap<String, String> map = new HashMap<>();
        map.put("phone", person.getPhone());
        map.put("name", person.getName());
        map.put("address", person.getAddress());
        if (person.getId()!=0){
            map.put("id", person.getId()+"");
            retrofit.create(DemoService.class).updatePeople(map)
                    .enqueue(new Callback<BaseResponse<Person>>() {
                        @Override
                        public void onResponse(Call<BaseResponse<Person>> call,final Response<BaseResponse<Person>> response) {
                            if (response.body().getStatus()==0){
                                executor.execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        personDao.insertAll(response.body().getData());
                                    }
                                });
                            }
                        }

                        @Override
                        public void onFailure(Call<BaseResponse<Person>> call, Throwable t) {

                        }
                    });
        }else {
            retrofit.create(DemoService.class).savePeople(map)
                    .enqueue(new Callback<BaseResponse<Person>>() {
                        @Override
                        public void onResponse(Call<BaseResponse<Person>> call,final Response<BaseResponse<Person>> response) {
                            if (response.body().getStatus()==0){
                                executor.execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        personDao.insertAll(response.body().getData());
                                    }
                                });
                            }
                        }

                        @Override
                        public void onFailure(Call<BaseResponse<Person>> call, Throwable t) {

                        }
                    });
        }

    }

    public void removePerson(final int id){

        retrofit.create(DemoService.class).deletePeople(id)
                .enqueue(new Callback<BaseResponse>() {
                    @Override
                    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                        if (response.body().getStatus()==0){
                            executor.execute(new Runnable() {
                                @Override
                                public void run() {
                                    Person p =new Person();
                                    p.setId(id);
                                    personDao.delete(p);
                                }
                            });
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseResponse> call, Throwable t) {

                    }
                });
    }

    public void clear(){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                personDao.deleteAll();
            }
        });
    }
}
