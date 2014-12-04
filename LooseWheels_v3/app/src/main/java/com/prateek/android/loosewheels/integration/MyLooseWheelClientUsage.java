package com.prateek.android.loosewheels.integration;

import com.loopj.android.http.RequestParams;

import org.json.JSONException;

public class MyLooseWheelClientUsage {
    public void getStream(String url, RequestParams params, JsonHandler jsonHandler) throws JSONException {
        LooseWheelClient.get(url, params, jsonHandler);
    }

    public void postStream(String url, RequestParams params, JsonHandler jsonHandler) throws JSONException {
        LooseWheelClient.post(url, params, jsonHandler);
    }
}
