package com.example.wiproassignment.ui;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.MutableLiveData;

import com.example.wiproassignment.R;
import com.example.wiproassignment.application.MyApplication;
import com.example.wiproassignment.base.RichMediatorLiveData;
import com.example.wiproassignment.models.AboutCanadaResponseModel;
import com.example.wiproassignment.network.ApiInterface;
import com.example.wiproassignment.utils.NetworkUtil;
import com.example.wiproassignment.utils.SchedulerProvider.BaseSchedulerProvider;

import io.reactivex.observers.DisposableObserver;

public class AboutCanadaRepo {

    private Application mMyApplication;
    private NetworkUtil mNetworkUtil;
    
    private ApiInterface mApiInterface;
    private BaseSchedulerProvider mSchedulerProvider;

    /**
     * Parametrised Constructor
     *
     * @param application  MyApplication class inatance
     * @param network      Network util class instance
     * @param apiInterface ApiInterface class insance to About Canada APi
     * @param baseSchedulerProvider Base Scheduler to hit API in Background thread and get result into main thread
     * */
    public AboutCanadaRepo(Application application, NetworkUtil network, ApiInterface apiInterface, BaseSchedulerProvider baseSchedulerProvider) {
        this.mNetworkUtil = network;
        this.mMyApplication = application;
        this.mApiInterface = apiInterface;
        this.mSchedulerProvider = baseSchedulerProvider;
    }

    /**
     * Method to call About Canada API and get List Items
     *
     * @param aboutCanadaRespModelLiveData  AboutCanadaResponseModelLiveData to notify the observers when APi hits successfully, with
*                                      proper parsed response and notify with proper msg with gets any error
     *
     * @param mutableIsLoadingStateLiveData  mutableIsLoadingStateLiveData to notify the observers when we APi hitting starts
 *                                     and when it ends.
     *
     * */
    @SuppressLint("CheckResult")
    public void hitAboutCanadaApi(RichMediatorLiveData<AboutCanadaResponseModel> aboutCanadaRespModelLiveData,
                          MutableLiveData<Boolean> mutableIsLoadingStateLiveData) {

        mutableIsLoadingStateLiveData.setValue(true);

        if (!mNetworkUtil.isNetworkAvailable()) {
            mutableIsLoadingStateLiveData.setValue(false);
            aboutCanadaRespModelLiveData.setError(mMyApplication.getResources().getString(R.string.no_internet));
        } else {

            mApiInterface.getAboutCanadaList().subscribeOn(mSchedulerProvider.ioWorker())
                .observeOn(mSchedulerProvider.mainThread())
                .subscribeWith(new DisposableObserver<AboutCanadaResponseModel>() {
                    @Override
                    public void onNext(AboutCanadaResponseModel aboutCanadaResponseModel) {
                        mutableIsLoadingStateLiveData.setValue(false);
                        if (aboutCanadaResponseModel != null) {
                            /*
                            * Setting response model after getting the result
                            * */
                            aboutCanadaRespModelLiveData.setValue(aboutCanadaResponseModel);
                        } else {
                            /*
                            * Setting Error state if response model is null
                            * */
                            aboutCanadaRespModelLiveData.setError(mMyApplication.getResources().getString(R.string.error_try_again_later));
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        /*
                         * Setting Error if request failed
                         * */
                        mutableIsLoadingStateLiveData.setValue(false);
                        aboutCanadaRespModelLiveData.setError(e.getMessage());
                    }
                    @Override
                    public void onComplete() {}
                });

        }
    }

}
