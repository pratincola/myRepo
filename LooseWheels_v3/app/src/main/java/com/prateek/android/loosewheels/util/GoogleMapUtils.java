package com.prateek.android.loosewheels.util;

import java.util.ArrayList;
import java.util.List;

import android.location.Location;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;

public class GoogleMapUtils {

    public static void toggleStyle(GoogleMap googleMap) {
        if (GoogleMap.MAP_TYPE_NORMAL == googleMap.getMapType()) {
            googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        } else {
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        }
    }

    public static double getDistance(double lat1, double lon1, double lat2, double lon2) {
        double R = 6371000; // for haversine use R = 6372.8 km instead of 6371 km
        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        //double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return 2 * R * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        // simplify haversine:
        //return 2 * R * 1000 * Math.asin(Math.sqrt(a));
    }

    public static double getDistanceBetweenPoints(double lat1, double lng1, double lat2, double lng2 ){
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lng2 - lng1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double distanceInMeters = Math.round(6371000 * c);
        return distanceInMeters;
    }

    public static long getDistanceBetweenPoints2(double lat1, double lng1, double lat2, double lng2 ){
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lng2 - lng1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        long distanceInMeters = Math.round(6371000 * c);
        return distanceInMeters;
    }



    public static double convertMetersToMiles(long m){
        return m * 0.00062137;
    }

    public static Location convertLatLngToLocation(LatLng latLng) {
        Location loc = new Location("someLoc");
        loc.setLatitude(latLng.latitude);
        loc.setLongitude(latLng.longitude);
        return loc;
    }

    public static float bearingBetweenLatLngs(LatLng begin,LatLng end) {
        Location beginL= convertLatLngToLocation(begin);
        Location endL= convertLatLngToLocation(end);
        return beginL.bearingTo(endL);
    }

    public static String encodeMarkerForDirection(Marker marker) {
        return marker.getPosition().latitude + "," + marker.getPosition().longitude;
    }

    public static void fixZoomForLatLngs(GoogleMap googleMap, List<LatLng> latLngs) {
        if (latLngs!=null && latLngs.size() > 0) {
            LatLngBounds.Builder bc = new LatLngBounds.Builder();

            for (LatLng latLng: latLngs) {
                bc.include(latLng);
            }

            googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bc.build(), 50),4000,null);
        }
    }

    public static void fixZoomForMarkers(GoogleMap googleMap, List<Marker> markers) {
        if (markers!=null && markers.size() > 0) {
            LatLngBounds.Builder bc = new LatLngBounds.Builder();

            for (Marker marker : markers) {
                bc.include(marker.getPosition());
            }

            googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bc.build(), 50),4000,null);
        }
    }

    public static List<LatLng> getSampleLatLngs() {
        List<LatLng> latLngs = new ArrayList<LatLng>();
        latLngs.add(new LatLng(50.961813797827055,3.5168474167585373));
        latLngs.add(new LatLng(50.96085423274633,3.517405651509762));
        latLngs.add(new LatLng(50.96020550146382,3.5177918896079063));
        latLngs.add(new LatLng(50.95936754348453,3.518972061574459));
        latLngs.add(new LatLng(50.95877285446026,3.5199161991477013));
        latLngs.add(new LatLng(50.958179213755905,3.520646095275879));
        latLngs.add(new LatLng(50.95901719316589,3.5222768783569336));
        latLngs.add(new LatLng(50.95954430150347,3.523542881011963));
        latLngs.add(new LatLng(50.95873336312275,3.5244011878967285));
        latLngs.add(new LatLng(50.95955781702322,3.525688648223877));
        latLngs.add(new LatLng(50.958855004782116,3.5269761085510254));
        return latLngs;
    }
}
