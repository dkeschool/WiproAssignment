package com.example.wiproassignment;

import com.example.wiproassignment.application.MyApplication;
import com.example.wiproassignment.network.ApiClient;
import com.example.wiproassignment.network.ApiInterface;

public class MyApplicationTest extends MyApplication {

    public static final String BASE_URL = "http://localhost:8080/";
    private static MyApplicationTest myApplication;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public ApiInterface getApiInterface() {
        return ApiClient.getClient(BASE_URL).create(ApiInterface.class);
    }

    public static MyApplicationTest getMyApplication() {
        if (myApplication == null) {
            myApplication = new MyApplicationTest();
        }
        return myApplication;
    }
}
