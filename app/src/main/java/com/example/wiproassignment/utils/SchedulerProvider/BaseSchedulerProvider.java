package com.example.wiproassignment.utils.SchedulerProvider;

import io.reactivex.Scheduler;

public interface BaseSchedulerProvider {

    /*
    * Method to provide worker thread scheduler
    * */
    Scheduler ioWorker();

    /*
     * Method to provide main thread scheduler
     * */
    Scheduler mainThread();
}
