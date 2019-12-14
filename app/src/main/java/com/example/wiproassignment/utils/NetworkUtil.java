package com.example.wiproassignment.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.example.wiproassignment.R;

public class NetworkUtil {

    private final String TAG = NetworkUtil.class.getSimpleName();
    private Context _mContext;

    /**
     * Parameterised constructor
     *
     * @param context
     * */
    public NetworkUtil(Context context) {
        this._mContext = context;
    }

    /*
    * Method to check if the Network is available or not
    * */
    public boolean isNetworkAvailable() {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) _mContext
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnected();

        } catch (Exception e) {
            Log.e(TAG, _mContext.getResources().getString(R.string.unable_to_check_net), e);
        }
        return false;
    }

}
