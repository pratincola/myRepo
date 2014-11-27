package com.prateek.android.connexus;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.loopj.android.http.RequestParams;
import com.prateek.android.connexus.handler.MyImagesJsonHandler;

import org.json.JSONException;

import java.io.File;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SingleStreamFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SingleStreamFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class SingleStreamFragment extends Fragment implements OnDownloadListenerConnexusImages {

    private static final String TAG = "SingleStreamFragment";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    static final int REQUEST_TAKE_PHOTO = 1;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    MyImagesJsonHandler jsonHandler;
    ImageAdapter3 imageAdapter;
    GridView gridView;
    ImageView imgFavorite;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param index Parameter 1.
     * @param coverURL Parameter 2.
     * @param streamKey
     * @param streamName
     * @return A new instance of fragment ViewAllFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SingleStreamFragment newInstance(int index, String coverURL, String streamKey, String streamName){
        SingleStreamFragment fragment = new SingleStreamFragment();
        Bundle args = new Bundle();
        args.putInt("index", index);
        args.putString("coverURL", coverURL);
        args.putString("streamKey", streamKey);
        args.putString("streamName", streamName);
        fragment.setArguments(args);
        return fragment;
    }
    public int getShownIndex() {
        Log.i(TAG, " getShownIndex");
        return getArguments().getInt("index", 0);
    }
    public SingleStreamFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, " onCreate");
        super.onCreate(savedInstanceState);
        RequestParams params = new RequestParams();
        String stream;
        if(getArguments() != null ){
            stream = getArguments().getString("streamKey");
        }
        else {
            stream = "5629499534213120";
        }
        Log.i(TAG, " Display Stream for: " + stream);
        params.put("stream", stream);
        jsonHandler = new MyImagesJsonHandler(this);
        MyConnexusClientUsage client = new MyConnexusClientUsage();

        try {// default
            client.getStream(jsonHandler, params , "Stream.json");
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
        View view = inflater.inflate(R.layout.fragment_single_stream, container, false);
        Log.i(TAG, " view is: " + view.toString());
        gridView = (GridView) view.findViewById(R.id.grid_view_all_images);
        Log.i(TAG, " grid view is: " + gridView.toString());
//        imageAdapter = new StreamAdapter2(view.getContext());
//        gridView.setAdapter(imageAdapter); // uses the view to get the context instead of getActivity().
//        gridView.setAdapter(new ImageAdapter3(view.getContext())); // uses the view to get the context instead of getActivity().
        ImageButton b = (ImageButton) view.findViewById(R.id.imgbtn2);
        b.setOnClickListener(new ImageButton.OnClickListener(){
            @Override
            public void onClick(View v) {
                open();
            }
        });
        imgFavorite = (ImageView) view.findViewById(R.id.image);

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
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
        super.onDetach();
        mListener = null;
    }

    @Override
    public String toString() {
        return "SingleStreamFragment{" +
                "mParam1='" + mParam1 + '\'' +
                ", mParam2='" + mParam2 + '\'' +
                ", mListener=" + mListener +
                ", jsonHandler=" + jsonHandler +
                ", imageAdapter=" + imageAdapter +
                ", gridView=" + gridView +
                '}';
    }

    public void onDownloadSuccess(List<ConnexusImages> c) {
        Log.i(TAG, " Holy Moley something GOOD happened..." + c.toString());
//        imageAdapter = new StreamAdapter2(getView().getContext());
//        imageAdapter.mThumbIds.addAll(c);
//        gridView.setAdapter(imageAdapter); // uses the view to get the context instead of getActivity().
    }


    public void onDownloadSuccess(ConnexusImages c) {
        Log.i(TAG, " Holy Moley ..." + c.toString());
        imageAdapter = new ImageAdapter3(getView().getContext());
        imageAdapter.mThumbIds.addAll(c.images);
        Log.i(TAG, " Holy Moley ..." + imageAdapter.toString());
        Log.i(TAG, " Holy Moley ..." + gridView.toString());
        imageAdapter.notifyDataSetChanged();
        gridView.setAdapter(imageAdapter); // uses the view to get the context instead of getActivity().

    }


    public void onDownloadFailure() {
        Log.i(TAG, " Crap something bad happened...");
    }

    // TODO: MUST DO...
    public void updateSingleStreamView(int position) {
        Log.i(TAG, " Update image instead of creating a new fragment...");
//        TextView article = (TextView) getActivity().findViewById(R.id.article);
//        article.setText(Ipsum.Articles[position]);
//        mCurrentPosition = position;
          imageAdapter.notifyDataSetChanged();

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
        public void onSingleStreamFragmentInteraction(String coverURL, String streamKey, String streamName, int position);

    }


    public void open(){
        Log.i(TAG, " open does work");
//        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//        startActivityForResult(intent, 0);
        takePhoto();
    }
    Uri imageUri;
    public void takePhoto() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        File photo = new File(Environment.getExternalStorageDirectory(),  "Pic.jpg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photo));
        imageUri = Uri.fromFile(photo);
        getActivity().startActivityForResult(intent, 100);
        Log.i(TAG, " " + imageUri);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i(TAG, " requestcode: " + requestCode);
        Log.i(TAG, " resultCode: " + resultCode);
        switch (requestCode) {
            case 100:
                if (resultCode == Activity.RESULT_OK) {
                    Uri selectedImage = imageUri;
                    getActivity().getContentResolver().notifyChange(selectedImage, null);
                    ContentResolver cr = getActivity().getContentResolver();
                    Bitmap bitmap;
                    try {
                        bitmap = android.provider.MediaStore.Images.Media.getBitmap(cr, selectedImage);
                        imgFavorite.setImageBitmap(bitmap);
                        Log.i(TAG, " butmap "+ bitmap.toString());
                        Log.i(TAG, " img view " + imgFavorite.toString());
                        Log.i(TAG, " img uri "+ imageUri.toString());
                        imageAdapter.addmThumbIds(imageUri.toString());
                    } catch (Exception e) {
                        Log.e("Camera", e.toString());
                    }
                    finally {
                        imageAdapter.notifyDataSetChanged();
                    }
                }
        }
    }
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        // TODO Auto-generated method stub
//        Log.i(TAG, " onActivityResult does work");
//        super.onActivityResult(requestCode, resultCode, data);
//        Bitmap bp = (Bitmap) data.getExtras().get("data");
//        imgFavorite.setImageBitmap(bp);
//    }

}
