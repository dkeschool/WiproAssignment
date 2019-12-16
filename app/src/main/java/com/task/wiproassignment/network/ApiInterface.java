package com.task.wiproassignment.network;

import com.task.wiproassignment.pojo.AboutCanadaResponseModel;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("s/2iodh4vg0eortkl/facts.json")
    Observable<AboutCanadaResponseModel> getAboutCanadaList();

}
