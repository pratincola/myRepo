package com.prateek.android.connexus;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.squareup.picasso.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prateek on 11/7/14.
 */
public class ImageAdapter3 extends BaseAdapter {

        private static final String TAG = "ImageAdapter3";
        private Context mContext;
        public ArrayList<String> mThumbIds = new ArrayList<String>(){};

        public ImageAdapter3(Context mContext) {
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

        public void addmThumbIds(String items) {
            this.mThumbIds.add(items);
        }



    @Override
    public String toString() {
        return "ImageAdapter3{" +
                "mContext=" + mContext +
                ", mThumbIds=" + mThumbIds +
                '}';
    }

    @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Log.i(TAG, " getView " + position + mThumbIds.get(position).toString());
            ImageView imageView = new ImageView(mContext);

                if (!mThumbIds.get(position).isEmpty()) {
                    Picasso.with(mContext)
                            .load(mThumbIds.get(position))
                            .placeholder(R.drawable.pic_1)
                            .error(R.drawable.ic_launcher)
                            .resize(150, 150)
                            .centerCrop()
                            .into(imageView);
                } else {
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

