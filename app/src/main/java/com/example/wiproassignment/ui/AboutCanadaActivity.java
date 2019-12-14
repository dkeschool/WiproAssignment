package com.example.wiproassignment.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.view.View;

import com.example.wiproassignment.R;
import com.example.wiproassignment.application.MyApplication;
import com.example.wiproassignment.base.BaseActivity;
import com.example.wiproassignment.databinding.ActivityAboutCanadaBinding;
import com.example.wiproassignment.network.ApiInterface;
import com.example.wiproassignment.utils.NetworkUtil;
import com.example.wiproassignment.utils.SchedulerProvider.BaseSchedulerProvider;
import com.example.wiproassignment.utils.SchedulerProvider.SchedulerProvider;
import com.example.wiproassignment.utils.ViewModelFactory;

public class AboutCanadaActivity extends BaseActivity {

    private ActivityAboutCanadaBinding mActivityAboutCanadaBinding;
    private AboutCanadaListAdapter mAboutCanadaListAdapter;
    private AboutCanadaViewModel mAboutCanadaViewModel;
    private NetworkUtil mNetworkUtil;
    private ApiInterface mApiInterface;

    private BaseSchedulerProvider mBaseSchedulerProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_canada);

        mActivityAboutCanadaBinding = DataBindingUtil.setContentView(this, R.layout.activity_about_canada);

        mNetworkUtil = new NetworkUtil(this);
        mApiInterface = MyApplication.getApplicationInstance().getApiInterface();
        mBaseSchedulerProvider = new SchedulerProvider();

        // setting toolbar
        toolbarSetup(mActivityAboutCanadaBinding.toolbar.toolbarCommon);

        // setting recycler view and attaching adapter to it
        recyclerViewSetup();

        // initialize view model using view model factory
        mAboutCanadaViewModel = ViewModelProviders.of(this,
            new ViewModelFactory(new AboutCanadaRepo(MyApplication.getApplicationInstance(), mNetworkUtil,
                mApiInterface, mBaseSchedulerProvider))).get(AboutCanadaViewModel.class);

        // observe data and set it into recycler view
        observeViewModelData();

        // setting pull to refresh for updated data
        pullToRefreshSetup();

        // hitting About Canda Api
        mAboutCanadaViewModel.hitAboutCanadaApi();
    }

    @Override
    protected int getResourceId() {
        return 0;
    }

    /*
    * Method to initialise pull to refresh listener
    * */
    private void pullToRefreshSetup() {
        mActivityAboutCanadaBinding.swipeRefreshLayout.setOnRefreshListener(() -> observeViewModelData());
    }

    /*
    * Method to initialise live data observers
    * */
    private void observeViewModelData() {

        // observing data from api call
        mAboutCanadaViewModel.getAboutCanadaResponseLiveData().observe(this, aboutCanadaModel -> {
            if (aboutCanadaModel != null) {
                mAboutCanadaListAdapter.submitList(aboutCanadaModel.getRows());
                getSupportActionBar().setTitle(aboutCanadaModel.getTitle());
            }
            if (mActivityAboutCanadaBinding.swipeRefreshLayout.isRefreshing())
                mActivityAboutCanadaBinding.swipeRefreshLayout.setRefreshing(false);
        });

        // observing loading state here
        mAboutCanadaViewModel.getLoadingStateLiveData().observe(this, isLoading -> {
            if (isLoading != null && isLoading)
                mActivityAboutCanadaBinding.textViewLoading.setVisibility(View.VISIBLE);
            else
                mActivityAboutCanadaBinding.textViewLoading.setVisibility(View.GONE);
        });

        // observing error message here
        mAboutCanadaViewModel.getErrorMessageLiveData().observe(this, msg -> {
            if (mActivityAboutCanadaBinding.swipeRefreshLayout.isRefreshing())
                mActivityAboutCanadaBinding.swipeRefreshLayout.setRefreshing(false);

            if (msg != null)
                AboutCanadaActivity.this.showSnackBar(mActivityAboutCanadaBinding.rootLayout, msg);
        });

    }

    /*
    * Method to setup the recycler view of About Canda items
    * */
    private void recyclerViewSetup() {
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.bg_recycler_separator));
        mActivityAboutCanadaBinding.recyclerViewExercise.addItemDecoration(dividerItemDecoration);

        mAboutCanadaListAdapter = new AboutCanadaListAdapter(this);
        mActivityAboutCanadaBinding.recyclerViewExercise.setAdapter(mAboutCanadaListAdapter);
    }

}
