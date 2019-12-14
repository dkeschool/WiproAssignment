package com.example.wiproassignment.network;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit mRetrofitClientInstance = null;

    /**
    * Method to create Retrofit client to hit API's
     *
     * @param baseUrl Base url of the API's
    * */
    public static Retrofit getClient(String baseUrl) {

        if (mRetrofitClientInstance == null) {
            mRetrofitClientInstance = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }

        return mRetrofitClientInstance;
    }
}
