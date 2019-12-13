package com.example.wiproassignment.utils.SchedulerProvider;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class SchedulerProviderTest implements BaseSchedulerProvider {

    @Override
    public Scheduler ioWorker() {
        return Schedulers.trampoline();
    }

    @Override
    public Scheduler mainThread() {
        return Schedulers.trampoline();
    }

}
