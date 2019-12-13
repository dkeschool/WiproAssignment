package com.example.wiproassignment.network;

import com.example.wiproassignment.models.CanadaDataModel;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("s/2iodh4vg0eortkl/facts.json")
    Observable<CanadaDataModel> getExerciseList();

}
