package com.example.wiproassignment.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Patterns;

import com.example.wiproassignment.R;
import com.example.wiproassignment.models.AboutCanadaResponseModel;;

public class AboutCanadaViewModel extends ViewModel {

    private AboutCanadaRepo mAboutCanadaRepo;
    private MutableLiveData<Boolean> mMutableIsLoading;
    private MutableLiveData<String> mMutableErrorMessage;
    private MutableLiveData<AboutCanadaResponseModel> mAboutCanadaResponseModel;

    /**
     * Parameterised constructor
     *
     * @param aboutCanadaRepo  AboutCanadaRespository object
     * */
    public AboutCanadaViewModel(AboutCanadaRepo aboutCanadaRepo) {
        this.mAboutCanadaRepo = aboutCanadaRepo;
        initLiveData();
    }

    /*
     * Method to initLiveData's
     * */
    public void initLiveData() {
        mMutableErrorMessage = new MutableLiveData<>();
        mMutableIsLoading = new MutableLiveData<>();
        mAboutCanadaResponseModel = new MutableLiveData<>();
    }

    /**
     * Method used to hit About Canada api after checking validations
     **/
    public void hitAboutCanadaApi() {
        mAboutCanadaRepo.hitAboutCanadaApi(mAboutCanadaResponseModel, mMutableIsLoading, mMutableErrorMessage);
    }

    /**
    * Method to call About Canada API and get List Items
    * */
    public MutableLiveData<AboutCanadaResponseModel> getAboutCanadaResponseLiveData() {
        return mAboutCanadaResponseModel;
    }

    /*
    * Method to get Loading state to know whether API is hitting or not
    * */
    public MutableLiveData<Boolean> getLoadingStateLiveData() {
        return  mMutableIsLoading;
    }

    /*
     * Method to get Error state
     * */
    public MutableLiveData<String> getErrorMessageLiveData() {
        return mMutableErrorMessage;
    }

}
