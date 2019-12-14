package com.example.wiproassignment.ui;

import android.annotation.SuppressLint;
import android.arch.lifecycle.MutableLiveData;

import com.example.wiproassignment.R;
import com.example.wiproassignment.application.MyApplication;
import com.example.wiproassignment.models.AboutCanadaResponseModel;
import com.example.wiproassignment.network.ApiInterface;
import com.example.wiproassignment.utils.NetworkUtil;
import com.example.wiproassignment.utils.SchedulerProvider.BaseSchedulerProvider;

import io.reactivex.observers.DisposableObserver;

public class AboutCanadaRepo {

    private MyApplication mMyApplication;
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
    public AboutCanadaRepo(MyApplication application, NetworkUtil network, ApiInterface apiInterface, BaseSchedulerProvider baseSchedulerProvider) {
        this.mNetworkUtil = network;
        this.mMyApplication = application;
        this.mApiInterface = apiInterface;
        this.mSchedulerProvider = baseSchedulerProvider;
    }

    /**
     * Method to call About Canada API and get List Items
     *
     * @param aboutCanadaRespModelLiveData  AboutCanadaResponseModelLiveData to notify the observers when APi hits successfully, with
*                                      proper parsed response
     *
     * @param mutableIsLoadingStateLiveData  mutableIsLoadingStateLiveData to notify the observers when we APi hitting starts
 *                                     and when it ends.
     *
     *  @param mutableErrorMessageLiveData   mutableErrorMessageLiveData to notify the observers when we get any error while
     *                                 hitting the API
     * */
    @SuppressLint("CheckResult")
    public void hitAboutCanadaApi(MutableLiveData<AboutCanadaResponseModel> aboutCanadaRespModelLiveData,
          MutableLiveData<Boolean> mutableIsLoadingStateLiveData, MutableLiveData<String> mutableErrorMessageLiveData) {

        mutableIsLoadingStateLiveData.setValue(true);

        if (!mNetworkUtil.isNetworkAvailable()) {
            mutableIsLoadingStateLiveData.setValue(false);
            mutableErrorMessageLiveData.setValue(mMyApplication.getResources().getString(R.string.no_internet));
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
                            mutableErrorMessageLiveData.setValue(mMyApplication.getResources().getString(R.string.error_try_again_later));
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        /*
                         * Setting Error if request failed
                         * */
                        mutableIsLoadingStateLiveData.setValue(false);
                        mutableErrorMessageLiveData.setValue(e.getMessage());
                    }
                    @Override
                    public void onComplete() {}
                });

        }
    }

}
