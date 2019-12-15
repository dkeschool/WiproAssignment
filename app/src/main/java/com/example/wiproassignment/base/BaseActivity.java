package com.example.wiproassignment.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /*
     * Method to setup toolbar
     * */
    public void showSnackBar(View rootLayout, String msg) {
        Snackbar.make(rootLayout, msg, 3000).show();
    }

    /*
     * Method to setup toolbar
     * */
    public void toolbarSetup(Toolbar toolbar) {
        setSupportActionBar(toolbar);
    }

}
