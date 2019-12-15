package com.example.wiproassignment.base;

import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;

public abstract class RichMediatorLiveData<T> extends MediatorLiveData<T> {

    private MutableLiveData<String> errorLiveData = new MutableLiveData<>();

    protected abstract Observer<String> getErrorObserver();

    @Override
    protected void onInactive() {
        super.onInactive();
        removeSource(errorLiveData);
    }

    @Override
    protected void onActive() {
        super.onActive();
        addSource(errorLiveData, getErrorObserver());
    }

    public void setError(String msg) {
        errorLiveData.setValue(msg);
    }

}
