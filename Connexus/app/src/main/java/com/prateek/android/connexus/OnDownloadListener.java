package com.prateek.android.connexus;

import android.app.Fragment;
import android.util.Log;

import java.util.List;

/**
 * Created by prateek on 11/6/14.
 */
public abstract class OnDownloadListener extends Fragment{
    private static final String TAG = "OnDownloadListener";

    public void onDownloadSuccess(List<ConnexusStream> c) {
        Log.i(TAG, " Holy Moley something GOOD happened..." + c.toString());
    }

    public void onDownloadSuccess(ConnexusStream c) {
        Log.i(TAG, " Holy Moley ...");
    }

    public void onDownloadFailure() {
        Log.i(TAG, " Crap something bad happened...");
    }

//    public void onDownloadSuccess(List<ConnexusImages> c) {
//        Log.i(TAG, " Holy Moley something GOOD happened..." + c.toString());
//    }

    public void onDownloadSuccess(ConnexusImages c) {
        Log.i(TAG, " Holy Moley ...");
    }

}
