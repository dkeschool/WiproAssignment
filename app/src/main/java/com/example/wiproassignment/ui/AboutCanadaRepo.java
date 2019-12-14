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

    public AboutCanadaRepo(MyApplication application, NetworkUtil internetUtil, ApiInterface apiInterface, BaseSchedulerProvider baseSchedulerProvider) {
        this.mNetworkUtil = internetUtil;
        this.mMyApplication = application;
        this.mApiInterface = apiInterface;
        this.mSchedulerProvider = baseSchedulerProvider;
    }

    /*
     * Method to call About Canada API and get List Items
     * */
    @SuppressLint("CheckResult")
    public void hitAboutCanadaApi(MutableLiveData<AboutCanadaResponseModel> aboutCanadaRespModel,
                                  MutableLiveData<Boolean> mutableIsLoadingState, MutableLiveData<String> mutableErrorData) {

        mutableIsLoadingState.setValue(true);

        if (!mNetworkUtil.isNetworkAvailable()) {
            mutableIsLoadingState.setValue(false);
            mutableErrorData.setValue(mMyApplication.getResources().getString(R.string.no_internet));
        } else {

            mApiInterface.getExerciseList().subscribeOn(mSchedulerProvider.ioWorker())
                .observeOn(mSchedulerProvider.mainThread())
                .subscribeWith(new DisposableObserver<AboutCanadaResponseModel>() {
                    @Override
                    public void onNext(AboutCanadaResponseModel aboutCanadaResponseModel) {
                        mutableIsLoadingState.setValue(false);
                        if (aboutCanadaResponseModel != null) {
                            /*
                            * Setting response model after getting the result
                            * */
                            aboutCanadaRespModel.setValue(aboutCanadaResponseModel);
                        } else {
                            /*
                            * Setting Error state if response model is null
                            * */
                            mutableErrorData.setValue(mMyApplication.getResources().getString(R.string.error_try_again_later));
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        /*
                         * Setting Error if request failed
                         * */
                        mutableIsLoadingState.setValue(false);
                        mutableErrorData.setValue(e.getMessage());
                    }
                    @Override
                    public void onComplete() {}
                });

        }
    }

}
