package com.prateek.android.connexus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prateek on 11/8/14.
 */
public class ConnexusImages {
    ArrayList<String> images;
    String stream_id;
    String uploadUrl;

    public ConnexusImages(ArrayList images, String stream_id, String uploadUrl) {
        this.images = images;
        this.stream_id = stream_id;
        this.uploadUrl = uploadUrl;
    }

    @Override
    public String toString() {
        return "ConnexusImages{" +
                "images=" + images +
                ", stream_id='" + stream_id + '\'' +
                ", uploadUrl='" + uploadUrl + '\'' +
                '}';
    }
}
