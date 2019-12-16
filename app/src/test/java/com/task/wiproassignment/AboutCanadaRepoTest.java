package com.task.wiproassignment;

import android.app.Application;
import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.task.wiproassignment.application.MyApplication;
import com.task.wiproassignment.base.RichMediatorLiveData;
import com.task.wiproassignment.models.AboutCanadaListItemModel;
import com.task.wiproassignment.models.AboutCanadaResponseModel;
import com.task.wiproassignment.network.ApiInterface;
import com.task.wiproassignment.ui.AboutCanadaRepo;
import com.task.wiproassignment.utils.NetworkUtil;
import com.task.wiproassignment.utils.SchedulerProvider.SchedulerProviderTest;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.observers.TestObserver;
import io.reactivex.plugins.RxJavaPlugins;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class AboutCanadaRepoTest {

    /*
    * To Bypass the Main Thread check and immediately runs any tasks on your test thread
    *
    * Currently it is byPassing the Main Thread check of Live Data objects
    * */
    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Mock
    private MyApplication mMyApplication;
    @Mock
    private NetworkUtil mNetworkUtil;
    @Mock
    private ApiInterface mApiInterface;
    @Mock
    private AboutCanadaRepo mAboutCanadaRepo;

    private Observer<String> mErrorObserver;
    private RichMediatorLiveData<AboutCanadaResponseModel> mAboutCanadaRespModelLiveData;
    private MutableLiveData<Boolean> mMutableIsLoadingStateLiveData;

    @BeforeClass
    public static void setUpRxSchedulers() {
        Scheduler immediate = new Scheduler() {
            @Override
            public Worker createWorker() {
                return new ExecutorScheduler.ExecutorWorker(new Executor() {
                    @Override
                    public void execute(Runnable command) {

                    }
                }, true);
            }

            @Override
            public Disposable scheduleDirect(@NonNull Runnable run, long delay, @NonNull TimeUnit unit) {
                // this prevents StackOverflowErrors when scheduling with a delay
                return super.scheduleDirect(run, 0, unit);
            }


        };

        RxJavaPlugins.setInitIoSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitComputationSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitNewThreadSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitSingleSchedulerHandler(scheduler -> immediate);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> immediate);
    }
    
    @Before
    public void setUpAboutCanadaRepo() {
        MockitoAnnotations.initMocks(this);
        mAboutCanadaRepo = new AboutCanadaRepo(mMyApplication, mNetworkUtil, mApiInterface, new SchedulerProviderTest());

        mErrorObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        };

        mAboutCanadaRespModelLiveData = new RichMediatorLiveData<AboutCanadaResponseModel>() {
            @Override
            protected Observer<String> getErrorObserver() {
                return mErrorObserver;
            }
        };

        mMutableIsLoadingStateLiveData = new MutableLiveData<>();
    }

    @Test
    public void noInternetError() {
        Mockito.doReturn(true).when(mNetworkUtil).isNetworkAvailable();
        Resources resources = Mockito.mock(Resources.class);
        Mockito.doReturn(resources).when(mMyApplication).getResources();
        Mockito.doReturn("no internet error").when(resources).getString(R.string.no_internet);
    }

    @Test
    public void getErrorDataForExerciseListApi() {

        Mockito.doReturn(true).when(mNetworkUtil).isNetworkAvailable();

        AboutCanadaResponseModel responseModel = Mockito.mock(AboutCanadaResponseModel.class);

        Mockito.doReturn(Observable.error(new Exception())).when(mApiInterface).getAboutCanadaList();

        Resources resources = Mockito.mock(Resources.class);

        Mockito.doReturn(resources).when(mMyApplication).getResources();

        Mockito.doReturn("error").when(resources).getString(R.string.error_try_again_later);

        mAboutCanadaRepo.hitAboutCanadaApi(mAboutCanadaRespModelLiveData, mMutableIsLoadingStateLiveData);

        Observable<AboutCanadaResponseModel> actualResultsObservable = mApiInterface.getAboutCanadaList();
        TestObserver<AboutCanadaResponseModel> testObserver = actualResultsObservable.test();
        testObserver.assertSubscribed();
        testObserver.assertError(Exception.class);


    }

    @Test
    public void getSuccessfulDataForExerciseListApi() {

        Mockito.doReturn(true).when(mNetworkUtil).isNetworkAvailable();
        AboutCanadaResponseModel responseModel = Mockito.mock(AboutCanadaResponseModel.class);
        ArrayList<AboutCanadaListItemModel> responseListItems = Mockito.mock(ArrayList.class);

        Mockito.doReturn("toolbar title").when(responseModel).getTitle();
        Mockito.doReturn(responseListItems).when(responseModel).getRows();
        Mockito.doReturn(Observable.just(responseModel)).when(mApiInterface).getAboutCanadaList();
        mAboutCanadaRepo.hitAboutCanadaApi(mAboutCanadaRespModelLiveData, mMutableIsLoadingStateLiveData);

        Observable<AboutCanadaResponseModel> actualResultsObservable = mApiInterface.getAboutCanadaList();
        TestObserver<AboutCanadaResponseModel> testObserver = actualResultsObservable.test();
        testObserver.assertSubscribed();
        testObserver.assertResult(responseModel);
    }


}
