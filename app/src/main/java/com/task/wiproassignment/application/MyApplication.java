package com.task.wiproassignment.application;

import android.app.Application;

import com.task.wiproassignment.network.ApiClient;
import com.task.wiproassignment.network.ApiInterface;

public class MyApplication extends Application {

    public static final String BASE_URL = "https://dl.dropboxusercontent.com/";
    private static MyApplication mMyAppInstance;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public ApiInterface getApiInterface() {
        return ApiClient.getClient(BASE_URL).create(ApiInterface.class);
    }

    /*
    * static method to get the instance of Application class
    * */
    public static MyApplication getApplicationInstance() {
        if (mMyAppInstance == null) {
            mMyAppInstance = new MyApplication();
        }
        return mMyAppInstance;
    }

}
