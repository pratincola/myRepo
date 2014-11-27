package com.prateek.android.connexus;

import android.app.Fragment;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by prateek on 11/6/14.
 */
public class StreamAdapter2 extends BaseAdapter{

    private static final String TAG = "StreamAdapter2";
    private Context mContext;
    public ArrayList<ConnexusStream> mThumbIds = new ArrayList<ConnexusStream>(){};

    public StreamAdapter2(Context mContext) {
        Log.i(TAG, " Initializing the ImageAdapter");
        this.mContext = mContext;

    }

    public int getCount() {
        Log.i(TAG, " getCount: " + mThumbIds.toString());
        return mThumbIds.size();

    }

    @Override
    public Object getItem(int position) {
        Log.i(TAG, " getItem");
        return mThumbIds.get(position);
    }


    @Override
    public long getItemId(int position) {
        Log.i(TAG, " getItemId" + position);
        return position;
    }

    public String getCover(int position){
        Log.i(TAG, " getCoverURL " + mThumbIds.get(position).cover_url );
        return mThumbIds.get(position).cover_url;
    }

    public String getStreamKey(int position){
        Log.i(TAG, " getStreamKey " + mThumbIds.get(position).key);
        return mThumbIds.get(position).key;
    }

    public String getStreamName(int position){
        Log.i(TAG, " getStreamName " + mThumbIds.get(position).stream_name);
        return mThumbIds.get(position).stream_name;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i(TAG, " getView " + position + mThumbIds.get(position).toString());
        ImageView imageView = new ImageView(mContext);

        if (!mThumbIds.get(position).cover_url.isEmpty()){
            Picasso.with(mContext)
                    .load(mThumbIds.get(position).cover_url)
                    .placeholder(R.drawable.pic_1)
                    .error(R.drawable.ic_launcher)
                    .resize(150, 150)
                    .centerCrop()
                    .into(imageView);
        }
        else {
            Picasso.with(mContext)
                    .load(R.drawable.ic_launcher)
                    .placeholder(R.drawable.pic_1)
                    .error(R.drawable.ic_launcher)
                    .noFade().resize(120, 120)
                    .centerCrop()
                    .into(imageView);
        }
        return imageView;

    }

}
