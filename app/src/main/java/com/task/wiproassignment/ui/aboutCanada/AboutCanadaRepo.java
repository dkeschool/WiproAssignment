package com.task.wiproassignment.ui.aboutCanada;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.text.TextUtils;

import com.task.wiproassignment.R;
import com.task.wiproassignment.base.RichMediatorLiveData;
import com.task.wiproassignment.pojo.AboutCanadaListItemModel;
import com.task.wiproassignment.pojo.AboutCanadaResponseModel;
import com.task.wiproassignment.network.ApiInterface;
import com.task.wiproassignment.utils.NetworkUtil;
import com.task.wiproassignment.utils.SchedulerProvider.BaseSchedulerProvider;

import java.util.ArrayList;
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
     *                                 and when it ends.
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
                             * Removing items having all the values as null
                             * */
                            if(aboutCanadaResponseModel.getRows()!=null)
                                removingNullItems(aboutCanadaResponseModel.getRows());

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

    /**
    * Method to remove items from list which are having all the 3 items as null
    *
    * @param mList AboutCanada List which is needed to be filter from null items
    * */
    private void removingNullItems(ArrayList<AboutCanadaListItemModel> mList) {
        for (int i = 0; i < mList.size(); i++)
            if (TextUtils.isEmpty(mList.get(i).getTitle()) && TextUtils.isEmpty(mList.get(i).getDescription()) &&
                    TextUtils.isEmpty(mList.get(i).getImageHref())) {
                mList.remove(i);
                i--;
            }
    }

}
