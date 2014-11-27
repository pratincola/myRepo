package com.prateek.android.connexus;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.prateek.android.connexus.handler.MyJsonHandler;

import org.json.JSONException;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ViewAllFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ViewAllFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class ViewAllFragment extends OnDownloadListener {

    private static final String TAG = "ViewAllFragment";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    MyJsonHandler jsonHandler;
    StreamAdapter2 imageAdapter;
    GridView gridView;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ViewAllFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewAllFragment newInstance(String param1, String param2) {
        ViewAllFragment fragment = new ViewAllFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public ViewAllFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        jsonHandler = new MyJsonHandler(this);
        MyConnexusClientUsage client = new MyConnexusClientUsage();

        try {
            client.getStream(jsonHandler, null , "Streams.json");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, " onCreateView ");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_all, container, false);
        Log.i(TAG, " view is: " + view.toString());
        gridView = (GridView) view.findViewById(R.id.grid_view_all);


//        imageAdapter = new StreamAdapter2(view.getContext());
//        gridView.setAdapter(imageAdapter); // uses the view to get the context instead of getActivity().
//        gridView.setAdapter(new ImageAdapter3(view.getContext())); // uses the view to get the context instead of getActivity().
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, " onItemClick " + position);
                // callback to activity
                mListener.onStreamSelectedInViewAllFragmentInteraction(imageAdapter.getCover(position),
                        imageAdapter.getStreamKey(position),
                        imageAdapter.getStreamName(position),
                        position);
            }
        });

        return view;
    }
//
//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(int position) {
//        if (mListener != null) {
//            mListener.onStreamSelectedInViewAllFragmentInteraction(, position, , );
//        }
//    }

    @Override
    public void onAttach(Activity activity) {
        Log.i(TAG, " ====> onAttach <==== ");
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
        Log.i(TAG, " ====> onDetach <==== ");
        super.onDetach();
        mListener = null;

    }

    @Override
    public void onDownloadSuccess(List<ConnexusStream> c) {
        Log.i(TAG, " Holy Moley something GOOD happened..." + c.toString());
        imageAdapter = new StreamAdapter2(getView().getContext());
        imageAdapter.mThumbIds.addAll(c);
        gridView.setAdapter(imageAdapter); // uses the view to get the context instead of getActivity().

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onStreamSelectedInViewAllFragmentInteraction(String coverURL, String streamKey, String streamName, int position);

    }

}
