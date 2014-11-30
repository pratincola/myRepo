package com.prateek.android.loosewheels.integration;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;
import com.loopj.android.http.JsonHttpResponseHandler;


/**
 * Created by prateek on 11/28/14.
 */
public abstract class JsonHandler extends JsonHttpResponseHandler {



    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
        super.onFailure(statusCode, headers, throwable, errorResponse);


//        fragment.onDownloadFailure();
    }


    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
        super.onFailure(statusCode, headers, throwable, errorResponse);
//        fragment.onDownloadFailure();
    }


    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
        super.onFailure(statusCode, headers, responseString, throwable);
    }

}
