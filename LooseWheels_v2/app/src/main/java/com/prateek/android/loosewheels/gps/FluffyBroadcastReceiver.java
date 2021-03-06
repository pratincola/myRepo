package com.prateek.android.loosewheels.gps;

/**
 * Created by prateek on 11/28/14.
 */
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.util.Log;

import com.google.android.gms.location.LocationClient;
import com.google.gson.Gson;
import com.littlefluffytoys.littlefluffylocationlibrary.LocationInfo;
import com.littlefluffytoys.littlefluffylocationlibrary.LocationLibraryConstants;
import com.loopj.android.http.RequestParams;
import com.prateek.android.loosewheels.MainActivity;
import com.prateek.android.loosewheels.R;
import com.prateek.android.loosewheels.integration.GPSData;
import com.prateek.android.loosewheels.integration.JsonHandler;
import com.prateek.android.loosewheels.integration.LooseWheelClient;
import com.prateek.android.loosewheels.integration.*;

import org.json.JSONException;

public class FluffyBroadcastReceiver extends BroadcastReceiver{
    private static final String TAG =  "FluffyBroadcastReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "FluffyBroadcastReceiver ------ onReceive: received location update");

            final LocationInfo locationInfo = (LocationInfo) intent
                    .getSerializableExtra(LocationLibraryConstants
                            .LOCATION_BROADCAST_EXTRA_LOCATIONINFO);

        Log.i(TAG, "SENDING JSON: received location update");

        Messages m = new Messages(locationInfo);
        m.sendPushUserData();
        m.sendGetUserData();


//            // For API 16+ use Notification.Builder instead of Notification
//            Notification notification = new Notification(R.drawable.ic_launcher,
//                    "Locaton updated " +
//                            locationInfo.getTimestampAgeInSeconds() +
//                            " seconds ago", System.currentTimeMillis());
//
//            Intent contentIntent = new Intent(context, MainActivity.class);
//            PendingIntent contentPendingIntent = PendingIntent.getActivity(context,
//                    0, contentIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//            notification.setLatestEventInfo(context, "Location update broadcast received",
//                    "Timestamped " +
//                            LocationInfo
//                                    .formatTimeAndDay(locationInfo.lastLocationUpdateTimestamp, true),
//                    contentPendingIntent);
//
//            ((NotificationManager) context
//                    .getSystemService(Context.NOTIFICATION_SERVICE))
//                    .notify(1234, notification);
    }
}


//
//        final LocationInfo locationInfo = (LocationInfo) intent.getSerializableExtra(LocationLibraryConstants
//                .LOCATION_BROADCAST_EXTRA_LOCATIONINFO);
//
//        final LocationInfo locationInf = (LocationInfo) intent.getSerializableExtra
//                (LocationLibraryConstants.getLocationChangedPeriodicBroadcastAction());
//
//
//        Log.i(TAG, " <<<<<<<<<<<<<<locationInfo>>>>>>>>>>>> " + locationInfo.toString());
//
//        Location location = intent.getParcelableExtra(LocationClient.KEY_LOCATION_CHANGED);
//
//        Log.i(TAG, " <<<<<<<<<<<<<<location>>>>>>>>>>>> " + location.toString());
//
//
//        // For API 16+ use Notification.Builder instead of Notification
//        Notification notification = new Notification(R.drawable.ic_launcher,
//                "Locaton updated " + locationInfo.getTimestampAgeInSeconds() + " seconds ago",
//                System.currentTimeMillis());
//
//        Intent contentIntent = new Intent(context, MainActivity.class);
//        PendingIntent contentPendingIntent = PendingIntent.getActivity(context, 0, contentIntent,
//                PendingIntent.FLAG_UPDATE_CURRENT);
//
//
//
//        notification.setLatestEventInfo(context, "Location update broadcast received",
//                "Timestamped " +
//                        LocationInfo.formatTimeAndDay(locationInfo.lastLocationUpdateTimestamp, true),
//                contentPendingIntent);
//
//        ((NotificationManager) context
//                .getSystemService(Context.NOTIFICATION_SERVICE))
//                .notify(1234, notification);
//
//
//
//    }
//}