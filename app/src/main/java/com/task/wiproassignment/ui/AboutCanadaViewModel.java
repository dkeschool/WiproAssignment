package com.task.wiproassignment.ui;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import com.task.wiproassignment.base.RichMediatorLiveData;
import com.task.wiproassignment.models.AboutCanadaResponseModel;

public class AboutCanadaViewModel extends ViewModel {

    private AboutCanadaRepo mAboutCanadaRepo;
    private MutableLiveData<Boolean> mIsLoadingLiveData;

    private Observer<String> mErrorObserver;
    private RichMediatorLiveData<AboutCanadaResponseModel> mAboutCanadaResponseLiveData;


    /**
     * Parameterised constructor
     *
     * @param aboutCanadaRepo  AboutCanadaRespository instance
     * */
    public AboutCanadaViewModel(AboutCanadaRepo aboutCanadaRepo) {
        this.mAboutCanadaRepo = aboutCanadaRepo;
    }

    /*
    * Method to initialise and set observers
    * */
    void setGenericListeners(Observer<String> errorObserver) {
        this.mErrorObserver = errorObserver;
        initLiveData();
    }

    /*
     * Method to initLiveData's
     * */
    private void initLiveData() {
        if (mAboutCanadaResponseLiveData == null) {
            mAboutCanadaResponseLiveData = new RichMediatorLiveData<AboutCanadaResponseModel>() {
                @Override
                protected Observer<String> getErrorObserver() {
                    return mErrorObserver;
                }
            };
        }
        mIsLoadingLiveData = new MutableLiveData<>();
    }

    /**
     * Method used to hit About Canada api after checking validations
     **/
    void hitAboutCanadaApi() {
        mAboutCanadaRepo.hitAboutCanadaApi(mAboutCanadaResponseLiveData, mIsLoadingLiveData);
    }

    /**
    * Method to call About Canada API and get List Items
    * */
    MutableLiveData<AboutCanadaResponseModel> getAboutCanadaResponseLiveData() {
        return mAboutCanadaResponseLiveData;
    }

    /*
    * Method to get Loading state to know whether API is hitting or not
    * */
    MutableLiveData<Boolean> getLoadingStateLiveData() {
        return  mIsLoadingLiveData;
    }


}
