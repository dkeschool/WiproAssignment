package com.task.wiproassignment;

import com.task.wiproassignment.application.MyApplication;
import com.task.wiproassignment.network.ApiClient;
import com.task.wiproassignment.network.ApiInterface;

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
