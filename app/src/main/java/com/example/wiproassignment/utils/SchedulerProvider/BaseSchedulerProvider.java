package com.example.wiproassignment.utils.SchedulerProvider;

import io.reactivex.Scheduler;

public interface BaseSchedulerProvider {

    Scheduler ioWorker();

    Scheduler mainThread();
}
