package com.example.wiproassignment.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void showToastLong(CharSequence message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void showToastShort(CharSequence message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
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
