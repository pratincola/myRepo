package com.prateek.android.loosewheels;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.littlefluffytoys.littlefluffylocationlibrary.LocationInfo;
import com.prateek.android.loosewheels.db.UserObjectHistory;
import com.prateek.android.loosewheels.db.UserSession;
import com.prateek.android.loosewheels.integration.GPSData;
import com.prateek.android.loosewheels.db.UserObject;
import com.prateek.android.loosewheels.integration.GPSDataCallback;
import com.prateek.android.loosewheels.util.GoogleMapUtils;
import com.prateek.android.loosewheels.util.JSONParser;
import com.prateek.android.loosewheels.util.PolyUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Enumeration;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MapsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MapsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapsFragment extends Fragment implements
        GoogleMap.OnMarkerDragListener,
        GoogleMap.OnMapClickListener,
        GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationChangeListener {

    private static final String TAG = "MapsFragment";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ProgressDialog progressDialog;
    private OnFragmentInteractionListener mListener;
    private GoogleMap googleMap;
    UserObjectHistory userInfo = UserObjectHistory.getInstance();
    UserSession userSession = UserSession.getInstance();
//    MarkerOptions startMarker;
    MarkerOptions endMarker, buddy1, buddy2 ;
    Context context;
    Polyline line;
    Marker friend1;
    protected static final double GPS_FREQUENCY = 15000D;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MapsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MapsFragment newInstance(String param1, String param2) {
        Log.i(TAG, " MapsFragment - - - - - - - - - - - - - - -");
        MapsFragment fragment = new MapsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    public MapsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, " onCreate - - - - - - - - - - - - - - -");
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, " onCreateView - - - - - - - - - - - - - - -");

        // Inflate the layout for this fragment
        // Attempt 1
//        return inflater.inflate(R.layout.fragment_maps, container, false);
//        mMapView = (MapView) v.findViewById(R.id.map);
//        mMapView.onCreate(savedInstanceState);
//        mMapView.onResume();// needed to get the map to display immediately
//        googleMap = mMapView.getMap();


        // Attempt 3
//        View view = inflater.inflate(R.layout.fragment_maps, container, false);
//        MapFragment mMapFragment = MapFragment.newInstance();
//        GoogleMap mMap = mMapFragment.getMap();
//        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
//        transaction.add(R.id.map, mMapFragment).commit();
//        return view;


        // Attempt 2
        View v = (FrameLayout) inflater.inflate(R.layout.fragment_maps, container, false);
        googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        context = getActivity();
        try {
            MapsInitializer.initialize(context.getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        googleMap.setMyLocationEnabled(true);
        googleMap.setOnMarkerDragListener(this);
        googleMap.setOnMapClickListener(this);


//        startMarker = new MarkerOptions()
//                .flat(true)
//                .visible(false)
//                .draggable(false)
//                .snippet("You")
//
//                .icon(BitmapDescriptorFactory
//                        .defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));

        endMarker = new MarkerOptions()
                .flat(true)
                .visible(false)
                .draggable(true)
                .snippet("Finish")
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_RED));

       buddy1 = new MarkerOptions()
                .flat(true)
                .visible(false)
                .draggable(false)
                .position(new LatLng(0,0))
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));

       buddy2 = new MarkerOptions()
                .flat(true)
                .visible(false)
                .draggable(false)
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));

        friend1 = googleMap.addMarker(buddy1);
//        googleMap.addMarker(new MarkerOptions()
//                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE))
//                .position(new LatLng(0,0))
//                .flat(true)
//                .rotation(245));
//        googleMap.moveCamera(CameraUpdateFactory.new);
//        double latitude = 17.385044;
//        double longitude = 78.486671;
//
//        // create marker
//        MarkerOptions marker = new MarkerOptions().position(
//                new LatLng(latitude, longitude)).title("Hello Maps");
//
//        // Changing marker icon
//        marker.icon(BitmapDescriptorFactory
//                .defaultMarker(BitmapDescriptorFactory.HUE_ROSE));
//
//        // adding marker
//        googleMap.addMarker(marker);
//        CameraPosition cameraPosition = new CameraPosition.Builder()
//                .target(new LatLng(17.385044, 78.486671)).zoom(12).build();
//        googleMap.animateCamera(CameraUpdateFactory
//                .newCameraPosition(cameraPosition));


        return v;

    }


    @Override
    public void onDestroyView() {
        Log.i(TAG, " onDestroyView - - - - - - - - - - - - - - -");
        try {
            MapFragment f = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
            if (f != null)
                getFragmentManager().beginTransaction().remove(f).commit();
            super.onDestroyView();
        }
        catch (Exception e){
            Log.i(TAG, e.toString());
        }

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        Log.i(TAG, " onButtonPressed - - - - - - - - - - - - - - -");
        if (mListener != null) {
            mListener.onMapFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        Log.i(TAG, " onAttach - - - - - - - - - - - - - - -");
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        Log.i(TAG, " onDetach - - - - - - - - - - - - - - -");
        super.onDetach();
        mListener = null;
    }

    public void updateMap(Location location){
        Log.i(TAG, " Holy Moley ..." + location.toString());
        UserObject o = new UserObject("user", "" + location.getLatitude(), "" + location.getLongitude());

        userInfo.enqueue(o);
        userInfo.enqueueLoc(location);
        Log.i(TAG, "new co-ordinates: ========> " + o.getLast_lat() + o.getLast_long() );
        userSession.setCurrLat(location.getLatitude());
        userSession.setCurrLng(location.getLongitude());

        // To avoid the case where we don't have enough data points
        if(userInfo.size() > 1){
            UserObject lastInfo = userInfo.dequeue();
            Location dest= userInfo.dequeueLoc();

//            calcSpeed(location.getLatitude(), location.getLongitude(),
//                        lastInfo.getLast_lat_double(), lastInfo.getLast_long_double());
            calcSpeed(location, dest);

        }

        // Update friends locations
        if(!userSession.friends.isEmpty()){
            Enumeration e = userSession.friends.elements();
            while(e.hasMoreElements())
            {
                Log.i(TAG, " next friend ");
                updateFriendMarker((UserObject) e.nextElement());
            }
        }


        if(location.hasBearing()) {
            gotoMyLocation(location.getLatitude(), location.getLongitude(), location.getBearing());
        }
        else{
            gotoMyLocation(location.getLatitude(), location.getLongitude(), 0f);
        }

    }


    public void updateFriendMarker(UserObject u){
        if(!friend1.isVisible()){
            friend1.setVisible(true);
        }
        friend1.setSnippet(u.getUsername());

        friend1.setPosition(new LatLng(u.getLast_lat_double(), u.getLast_long_double()));
        Log.i(TAG, " updating friend location..... " + u.getLast_lat_double() + " "+ u.getLast_long_double());
    }

    public void calcSpeed(Location location, Location dest){
//        double dist = GoogleMapUtils.getDistanceBetweenPoints(lat1, lng1, lat2, lng2 );
//        double dist2 = GoogleMapUtils.getDistance(lat1, lng1, lat2, lng2 );
//        long dist3 = GoogleMapUtils.getDistanceBetweenPoints2(lat1, lng1, lat2, lng2 );
//
//        Log.i(TAG, " Dist is: " + dist + " " + dist2 + " " + dist3);
//
//        long speed = Math.round(dist/GPS_FREQUENCY);
//        double speed2 = Math.round(dist2/GPS_FREQUENCY);
//        long speed3 = Math.round(dist3/GPS_FREQUENCY);
//        Log.i(TAG, " SPEED is: " + speed + " " + speed2 + " " + speed3);
//        userSession.setCurSpeed(new Double(GoogleMapUtils.convertMetersToMiles(speed)));

//        float [] result = new float[3];
//        Location.distanceBetween(lat1,lng1, lat2, lng2, result);
        float f = location.distanceTo(dest);
        Log.i(TAG, "distance to: ========> " + f );

        // Update speed
        userSession.setCurSpeed( ((long) f/GPS_FREQUENCY));


        if(userSession.getDestLat() !=null && userSession.getDestLng() !=null){
            float [] results = new float [3];
            Location.distanceBetween(location.getLatitude(), location.getLongitude(),
                    userSession.getDestLat(), userSession.getDestLng(), results);
//            double t2 = GoogleMapUtils.getDistance(lat1, lng1, userSession.getDestLat(), userSession.getDestLng());
            userSession.setDistToDest(new Double( (long)results[0]));
            Log.i(TAG, " Dist to destination is: " + results[0] + " " );
        }

        Toast.makeText(context, "speed: " + userSession.getCurSpeed() + " " + userSession.getDistToDest() , Toast.LENGTH_LONG).show();

    }


    /**
     *
     * @param lat - latitude of the location to move the camera to
     * @param lng - longitude of the location to move the camera to
     *            Prepares a CameraUpdate object to be used with  callbacks
     */

    private void gotoMyLocation(double lat, double lng, float bear) {

        changeCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder().target(new LatLng(lat, lng))
                        .zoom(18f)
                        .tilt(20)
                        .bearing(bear)
                        .build()
        ), new GoogleMap.CancelableCallback() {
            @Override
            public void onFinish() {
                // Your code here to do something after the Map is rendered
            }

            @Override
            public void onCancel() {
                // Your code here to do something after the Map rendering is cancelled
            }
        });

//        googleMap.addPolyline(new PolylineOptions().geodesic(true)
//                .add(new LatLng(lat, lng)))  ;
    }


    private void changeCamera(CameraUpdate update, GoogleMap.CancelableCallback callback) {
//        googleMap.moveCamera(update);
        googleMap.animateCamera(update, 2000, null);
    }


    // Interface Methods
    @Override
    public void onMarkerDragStart(Marker marker) {
        Log.i(TAG, " onMarkerDragStart - - - - - - - - - - - - - - -");

    }

    @Override
    public void onMarkerDrag(Marker marker) {
        Log.i(TAG, " onMarkerDrag - - - - - - - - - - - - - - -");

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        Log.i(TAG, " onMarkerDragEnd - - - - - - - - - - - - - - -");
    }

    @Override
    public boolean onMyLocationButtonClick() {
        Log.i(TAG, " onMyLocationButtonClick - - - - - - - - - - - - - - -");
        return false;
    }

    @Override
    public void onMyLocationChange(Location location) {
        Log.i(TAG, " onMyLocationChange - - - - - - - - - - - - - - -");
    }

    @Override
    public void onMapClick(LatLng latLng) {
        Log.i(TAG, " onMapClick - - - - - - - - - - - - - - -");
        if(userInfo.hasItems() && !endMarker.isVisible()){
            endMarker.visible(true).position(latLng);
            googleMap.addMarker(endMarker);

            UserObject usr = userInfo.dequeue();
            userSession.setDestLat(endMarker.getPosition().latitude);
            userSession.setDestLng(endMarker.getPosition().longitude);

            LatLng currLoc = new LatLng(usr.getLast_lat_double(), usr.getLast_long_double());
            String urlTopass = makeURL(usr.getLast_lat_double(),
                    usr.getLast_long_double(), endMarker.getPosition().latitude,
                    endMarker.getPosition().longitude);

            Log.i(TAG, "URL FOR ----------- Direction ------ " + urlTopass);
            new connectAsyncTask(urlTopass).execute();


        }
    }


    public void onMenuClickStopTimer() {
        Log.i(TAG, " onMenuClickStopTimer - - - - - - - - - - - - - - -");
        // TODO: Call method to start speed tracking...

    }


     public void onMenuClickStartTimer() {
         Log.i(TAG, " onMenuClickStartTimer - - - - - - - - - - - - - - -");
         // Set the current location as the start location for the race
         userSession.setOriginLat(userSession.getCurrLat());
         userSession.setOriginLng(userSession.getCurrLng());

         Toast.makeText(getActivity(), "5" , Toast.LENGTH_SHORT).show();
         Toast.makeText(getActivity(), "4" , Toast.LENGTH_SHORT).show();
         Toast.makeText(getActivity(), "3" , Toast.LENGTH_SHORT).show();
         Toast.makeText(getActivity(), "2" , Toast.LENGTH_SHORT).show();
         Toast.makeText(getActivity(), "1" , Toast.LENGTH_SHORT).show();
         Toast.makeText(getActivity(), "START" , Toast.LENGTH_LONG).show();

         // TODO: Call method to start speed tracking...
//         LocationInfo l = new LocationInfo(getActivity());
     }

    // Async Activity
    private class connectAsyncTask extends AsyncTask<Void, Void, String> {
        private ProgressDialog progressDialog;
        String url;

        connectAsyncTask(String urlPass) {
            url = urlPass;
        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage("Fetching route, Please wait...");
            progressDialog.setIndeterminate(true);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(Void... params) {
            JSONParser jParser = new JSONParser();
            String json = jParser.getJSONFromUrl(url);
            return json;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            progressDialog.hide();
            if (result != null) {
                drawPath(result);
            }
        }
    }

    public void drawPath(String result) {
        // Static LatLng
//        LatLng startLatLng = new LatLng(30.707104, 76.690749);
//        LatLng endLatLng = new LatLng(30.721419, 76.730017);

//        if (line != null) {
//            googleMap.clear();
//        }
//        googleMap.addMarker(new MarkerOptions().position(endLatLng).icon(
//                BitmapDescriptorFactory.fromResource(R.drawable.common_signin_btn_icon_normal_dark)));
//        googleMap.addMarker(new MarkerOptions().position(startLatLng).icon(
//                BitmapDescriptorFactory.fromResource(R.drawable.common_signin_btn_icon_pressed_light)));
        try {
            // Tranform the string into a json object
            final JSONObject json = new JSONObject(result);
            Log.i(TAG, " Message from GOOGLE FOR DRAWING MAPS" + result.toString());
            JSONArray routeArray = json.getJSONArray("routes");
            JSONObject routes = routeArray.getJSONObject(0);
            JSONObject overviewPolylines = routes
                    .getJSONObject("overview_polyline");
            String encodedString = overviewPolylines.getString("points");
            List<LatLng> list = PolyUtil.decodePoly(encodedString);

            for (int z = 0; z < list.size() - 1; z++) {
                LatLng src = list.get(z);
                LatLng dest = list.get(z + 1);
                line = googleMap.addPolyline(new PolylineOptions()
                        .add(new LatLng(src.latitude, src.longitude),
                                new LatLng(dest.latitude, dest.longitude))
                        .width(5).color(Color.BLUE).geodesic(true));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String makeURL(double sourcelat, double sourcelog, double destlat,
                          double destlog) {
        StringBuilder urlString = new StringBuilder();
        urlString.append("http://maps.googleapis.com/maps/api/directions/json");
        urlString.append("?origin=");// from
        urlString.append(Double.toString(sourcelat));
        urlString.append(",");
        urlString.append(Double.toString(sourcelog));
        urlString.append("&destination=");// to
        urlString.append(Double.toString(destlat));
        urlString.append(",");
        urlString.append(Double.toString(destlog));
        urlString.append("&sensor=false&mode=bicycling&alternatives=true");
        return urlString.toString();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onMapFragmentInteraction(Uri uri);
    }

}
