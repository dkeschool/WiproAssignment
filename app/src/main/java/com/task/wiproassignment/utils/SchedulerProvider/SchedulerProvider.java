package com.task.wiproassignment.utils.SchedulerProvider;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SchedulerProvider implements BaseSchedulerProvider {

    /*
     * Method to get Worker thread scheduler
     * */
    @Override
    public Scheduler ioWorker() {
        return Schedulers.io();
    }

    /*
     * Method to get main thread scheduler
     * */
    @Override
    public Scheduler mainThread() {
        return AndroidSchedulers.mainThread();
    }
}
