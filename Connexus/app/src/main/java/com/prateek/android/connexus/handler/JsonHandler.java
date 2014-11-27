package com.prateek.android.connexus.handler;

import android.app.Fragment;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.prateek.android.connexus.OnDownloadListener;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by prateek on 11/8/14.
 */
public abstract class JsonHandler extends JsonHttpResponseHandler {
    OnDownloadListener fragment;


    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
        super.onFailure(statusCode, headers, throwable, errorResponse);
        fragment.onDownloadFailure();
    }


    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
        super.onFailure(statusCode, headers, throwable, errorResponse);
        fragment.onDownloadFailure();
    }


    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
        super.onFailure(statusCode, headers, responseString, throwable);
    }

}
