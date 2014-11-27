package com.prateek.android.connexus;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by prateek on 11/4/14.
 */
public class ImageAdapter extends BaseAdapter {
    private static final String TAG = "ImageAdapter";
    private Context mContext;
//    private ArrayList<ConnexusStream> mThumbIds;

    public Integer[] mThumbIds = {
            R.drawable.pic_1, R.drawable.pic_2,
            R.drawable.pic_3, R.drawable.pic_4,
            R.drawable.pic_5, R.drawable.pic_6,
            R.drawable.pic_7, R.drawable.pic_8,
            R.drawable.pic_9, R.drawable.pic_10,
            R.drawable.pic_11, R.drawable.pic_12,
            R.drawable.pic_13, R.drawable.pic_14,
            R.drawable.pic_15
    };
    public ImageAdapter(Context mContext) {
        Log.i(TAG, " Initializing the ImageAdapter");
        this.mContext = mContext;

    }

    @Override
    public int getCount() {
        Log.i(TAG, " getCount: " + mThumbIds.toString());
        return mThumbIds.length;

    }

    @Override
    public Object getItem(int position) {
        return mThumbIds[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
//        return mThumbIds.get(position).id;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i(TAG, " getView " + position );
        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource((int) getItemId(position));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(70, 70));
        return imageView;
    }
}

