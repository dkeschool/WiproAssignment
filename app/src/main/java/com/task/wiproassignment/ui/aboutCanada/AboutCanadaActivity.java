package com.task.wiproassignment.ui.aboutCanada;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.text.TextUtils;
import android.view.View;

import com.task.wiproassignment.ui.adapters.AboutCanadaListAdapter;
import com.task.wiproassignment.databinding.ActivityAboutCanadaBinding;
import com.task.wiproassignment.R;
import com.task.wiproassignment.application.MyApplication;
import com.task.wiproassignment.base.BaseActivity;
import com.task.wiproassignment.network.ApiInterface;
import com.task.wiproassignment.utils.NetworkUtil;
import com.task.wiproassignment.utils.SchedulerProvider.BaseSchedulerProvider;
import com.task.wiproassignment.utils.SchedulerProvider.SchedulerProvider;
import com.task.wiproassignment.utils.ViewModelFactory;

import java.util.Objects;

public class AboutCanadaActivity extends BaseActivity {

    private ActivityAboutCanadaBinding mActivityAboutCanadaBinding;
    private AboutCanadaListAdapter mAboutCanadaListAdapter;
    private AboutCanadaViewModel mAboutCanadaViewModel;
    private NetworkUtil mNetworkUtil;
    private ApiInterface mApiInterface;
    private Observer<String> mErrorObserver;

    private BaseSchedulerProvider mBaseSchedulerProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_canada);

        mActivityAboutCanadaBinding = DataBindingUtil.setContentView(this, R.layout.activity_about_canada);

        init();

        // initialize view model using view model factory
        mAboutCanadaViewModel = ViewModelProviders.of(this,
                new ViewModelFactory(new AboutCanadaRepo(getApplication(), mNetworkUtil,
                mApiInterface, mBaseSchedulerProvider))).get(AboutCanadaViewModel.class);
        mAboutCanadaViewModel.setGenericListeners(getmErrorObserver());

        // setting toolbar
        toolbarSetup(mActivityAboutCanadaBinding.toolbar.toolbarCommon);

        // observing data and set it into recycler view
        observeViewModelData();

        // setting recycler view and attaching adapter to it
        recyclerViewSetup();

        // setting pull to refresh for updated data
        pullToRefreshSetup();

        // hitting About Canda Api
        mAboutCanadaViewModel.hitAboutCanadaApi();
    }


    /*
    * Method to initialise
    * */
    private void init() {
        mNetworkUtil = new NetworkUtil(this);
        mApiInterface = MyApplication.getApplicationInstance().getApiInterface();
        mBaseSchedulerProvider = new SchedulerProvider();

        // Creating Observer of Error
        mErrorObserver = msg -> {
            if (mActivityAboutCanadaBinding.swipeRefreshLayout.isRefreshing())
                mActivityAboutCanadaBinding.swipeRefreshLayout.setRefreshing(false);

            if (msg != null)
                AboutCanadaActivity.this.showSnackBar(mActivityAboutCanadaBinding.rootLayout, msg);
        };
    }

    /*
    * Getter method of error observer
    * */
    public Observer<String> getmErrorObserver() {
        return mErrorObserver;
    }

    /*
    * Method to initialise pull to refresh listener
    * */
    private void pullToRefreshSetup() {
        mActivityAboutCanadaBinding.swipeRefreshLayout.setOnRefreshListener(() -> mAboutCanadaViewModel.hitAboutCanadaApi());
    }

    /*
    * Method to initialise live data observers
    * */
    private void observeViewModelData() {

        // observing data from api call
        mAboutCanadaViewModel.getAboutCanadaResponseLiveData().observe(this, aboutCanadaModel -> {
            if (aboutCanadaModel != null && aboutCanadaModel.getRows()!=null) {
                mAboutCanadaListAdapter.submitList(aboutCanadaModel.getRows());
                mAboutCanadaListAdapter.notifyDataSetChanged();
                if(getSupportActionBar()!=null)
                    getSupportActionBar().setTitle(!TextUtils.isEmpty(aboutCanadaModel.getTitle())?aboutCanadaModel.getTitle():getString(R.string.app_name));
            } else
                AboutCanadaActivity.this.showSnackBar(mActivityAboutCanadaBinding.rootLayout, getString(R.string.error_try_again_later));

            if (mActivityAboutCanadaBinding.swipeRefreshLayout.isRefreshing())
                mActivityAboutCanadaBinding.swipeRefreshLayout.setRefreshing(false);
        });

        // observing loading state here
        mAboutCanadaViewModel.getLoadingStateLiveData().observe(this, isLoading -> {
            if (isLoading != null && isLoading)
                mActivityAboutCanadaBinding.tvLoading.setVisibility(View.VISIBLE);
            else
                mActivityAboutCanadaBinding.tvLoading.setVisibility(View.GONE);
        });

    }

    /*
    * Method to setup the recycler view of About Canda items
    * */
    private void recyclerViewSetup() {
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(this, R.drawable.bg_recycler_separator)));
        mActivityAboutCanadaBinding.rvAboutCanadaList.addItemDecoration(dividerItemDecoration);

        mAboutCanadaListAdapter = new AboutCanadaListAdapter(this);
        mActivityAboutCanadaBinding.rvAboutCanadaList.setAdapter(mAboutCanadaListAdapter);
    }

}
