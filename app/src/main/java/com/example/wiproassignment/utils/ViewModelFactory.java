package com.example.wiproassignment.utils;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

public class ViewModelFactory implements ViewModelProvider.Factory {

    /*private ExerciseRepository exerciseRepository;

    public ViewModelFactory(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }*/

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

       /* if (AboutCanadaViewModel.class.isAssignableFrom(modelClass)) {
            return (T) new AboutCanadaViewModel(exerciseRepository);
        }*/

        return null;
    }
}
