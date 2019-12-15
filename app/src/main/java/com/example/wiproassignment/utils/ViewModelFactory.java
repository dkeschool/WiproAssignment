package com.example.wiproassignment.utils;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.wiproassignment.ui.AboutCanadaRepo;
import com.example.wiproassignment.ui.AboutCanadaViewModel;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private AboutCanadaRepo mAboutCanadaRepo;

    /**
    * Parameterised constructor
    *
    * @param aboutCanadaRepo  AboutCanadaRespository instance
    * */
    public ViewModelFactory(AboutCanadaRepo aboutCanadaRepo) {
        this.mAboutCanadaRepo = aboutCanadaRepo;
    }

    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (AboutCanadaViewModel.class.isAssignableFrom(modelClass)) {
            return (T) new AboutCanadaViewModel(mAboutCanadaRepo);
        }
        return null;
    }
}
