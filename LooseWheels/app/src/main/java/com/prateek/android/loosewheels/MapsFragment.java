package com.prateek.android.loosewheels;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MapsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MapsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapsFragment extends Fragment {

    private static final String TAG = "MapsFragment";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private GoogleMap googleMap;


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

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        googleMap.setMyLocationEnabled(true);
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
        if(location.hasBearing()) {
            gotoMyLocation(location.getLatitude(), location.getLongitude(), location.getBearing());
        }
        else{
            gotoMyLocation(location.getLatitude(), location.getLongitude(), 0f);
        }



//        imageAdapter = new ImageAdapter3( getView().getContext());
//        imageAdapter.mThumbIds.addAll(c.images);
//        Log.i(TAG, " Holy Moley ..." + imageAdapter.toString());
//        Log.i(TAG, " Holy Moley ..." + gridView.toString());
//        imageAdapter.notifyDataSetChanged();
//        gridView.setAdapter(imageAdapter); // uses the view to get the context instead of getActivity().



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

        googleMap.addPolyline(new PolylineOptions().geodesic(true)
                .add(new LatLng(lat, lng)))  ;
    }


    private void changeCamera(CameraUpdate update, GoogleMap.CancelableCallback callback) {
//        googleMap.moveCamera(update);
        googleMap.animateCamera(update, 2000, null);
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
