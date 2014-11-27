package com.prateek.android.connexus;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by prateek on 11/4/14.
 */
public class ConnexusStream {
    String key;
    String stream_name;
    String cover_url;

    public ConnexusStream(String key, String stream_name, String cover_url) {
        this.key = key;
        this.stream_name = stream_name;
        this.cover_url = cover_url;
    }

    @Override
    public String toString() {
        return "ConnexusStream{" +
                "key='" + key + '\'' +
                ", name='" + stream_name + '\'' +
                ", coverImageUrl='" + cover_url + '\'' +
                '}';
    }
}