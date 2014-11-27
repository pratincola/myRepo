package com.prateek.android.connexus;

import java.util.Locale;
import java.util.Map;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.support.v13.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.loopj.android.http.RequestParams;
import com.prateek.android.connexus.handler.MyImagesJsonHandler;

import org.json.JSONException;


public class MainActivity extends Activity implements ActionBar.TabListener,
        LogInFragment.OnFragmentInteractionListener,
        NearByFragment.OnFragmentInteractionListener,
        ViewAllFragment.OnFragmentInteractionListener,
        SingleStreamFragment.OnFragmentInteractionListener {

    Map<Integer, String> mPageReferenceMap;


    enum TabType {
        LOGIN, NEARBY, VIEWALL
    }


    public static final String TAG = "MainActivity";
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v13.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the action bar.
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
            Log.i(TAG, "Creating Tabs");
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        Log.i(TAG, "onTabSelected before");
        mViewPager.setCurrentItem(tab.getPosition());
        Log.i(TAG, "onTabSelected after");
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        Log.i(TAG, "onTabUNselected before");
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        Log.i(TAG, "onTabREselected before");

    }

    @Override
    public void onLogInFragmentInteraction(View view) {
        Log.i(TAG, "onLogInFragmentInteraction: ");
    }

    @Override
    public void onNearByFragmentInteraction(Uri uri) {
        Log.i(TAG, "onNearByFragmentInteraction: ");
    }

    @Override
    public void onSingleStreamFragmentInteraction(String coverURL, String streamKey, String streamName, int position) {
        Log.i(TAG, "onSingleStreamFragmentInteraction: ");
    }
    MyImagesJsonHandler jsonHandler;
    @Override
    public void onStreamSelectedInViewAllFragmentInteraction(String coverURL, String streamKey, String streamName, int position) {
        Log.i(TAG, "onStreamSelectedInViewAllFragmentInteraction: ");

//        FragmentManager a = getFragmentManager();
//
//
//        SingleStreamFragment singleStreamFrag = (SingleStreamFragment)
//                getFragmentManager().findFragmentById(2);
//
//
//
//        Log.i(TAG, " singleStreamFrag..." +  singleStreamFrag.toString());
//        jsonHandler = new MyImagesJsonHandler(singleStreamFrag);
//        RequestParams params = new RequestParams();
//        params.put("stream", "5629499534213120");
//
//        MyConnexusClientUsage client = new MyConnexusClientUsage();
//        try {
//            client.getStream(jsonHandler, params , "Stream.json");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

//          Attempt3
//        Intent intent = new Intent(MainActivity.this, ViewSingleStreamActivity.class);
//        intent.putExtra("index", position);
//        intent.putExtra("coverURL", coverURL);
//        intent.putExtra("streamKey", streamKey);
//        intent.putExtra("streamName", streamName);
//        startActivity(intent);

//        Attempt2
        // Capture the article fragment from the activity layout
        Fragment c = mSectionsPagerAdapter.getRegisteredFragment(position);
        SingleStreamFragment singleStreamFrag = (SingleStreamFragment) mSectionsPagerAdapter.getRegisteredFragment(2);

        if (singleStreamFrag != null) {
            Log.i(TAG, "singleStreamFrag is not null: ");
            // If article frag is available, we're in two-pane layout...
            // Call a method in the ArticleFragment to update its content
            jsonHandler = new MyImagesJsonHandler(singleStreamFrag);
            RequestParams params = new RequestParams();
            params.put("stream", streamKey);
            MyConnexusClientUsage client = new MyConnexusClientUsage();
            try {
                client.getStream(jsonHandler, params , "Stream.json");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            singleStreamFrag.updateSingleStreamView(position);
        } else {
            // If the frag is not available, we're in the one-pane layout and must swap frags...

            // Create fragment and give it an argument for the selected article
            Log.i(TAG, "  singleStreamFrag is null: ");
            SingleStreamFragment newFragment = new SingleStreamFragment().newInstance(position, coverURL, streamKey, streamName);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            Log.i(TAG, "  creating transactions ");
            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
//            transaction.replace(R.id.pager, newFragment);
//
//            transaction.addToBackStack(null);
            Log.i(TAG, "  about to commit transaction: ");
            // Commit the transaction
            transaction.commit();
        }
        this.onTabSelected(getActionBar().getTabAt(2), getFragmentManager().beginTransaction());

//        Attempt1
////////////////////////////
//        SingleStreamFragment  singleStreamFragment = new SingleStreamFragment();
//        FragmentTransaction transaction = getFragmentManager().beginTransaction();
//        transaction.replace(R.id.pager, singleStreamFragment);
//        transaction.addToBackStack(null);
//        // Commit the transaction
//        transaction.commit();
////////////////////////////
//            SingleStreamFragment singleStreamFragment = (SingleStreamFragment) getFragmentManager()
//                    .findFragmentById(R.id.grid_view_all_images);
////            Log.i(TAG, " onStreamSelectedInViewAllFragment.. ");
//            if (singleStreamFragment == null ){
//                singleStreamFragment = SingleStreamFragment.newInstance(position, coverURL, streamKey, streamName);
//                FragmentTransaction ft = getFragmentManager().beginTransaction();
//                ft.replace(R.id.pager, singleStreamFragment);
//                ft.addToBackStack(null);
//////            else {
//////                ft.replace(R.id.a_item, details);
//////            }
//                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
//                ft.commit();
//            }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, " parent: requestcode: " + requestCode);
        Log.i(TAG, " parent: resultCode: " + resultCode);
        SingleStreamFragment singleStreamFrag = (SingleStreamFragment) mSectionsPagerAdapter.getRegisteredFragment(2);
        singleStreamFrag.onActivityResult(requestCode, resultCode, data);
    }

    private static String makeFragmentName(int viewId, int index) {
        return "android:switcher:" + viewId + ":" + index;
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        SparseArray<Fragment> registeredFragments = new SparseArray<Fragment>();


        private String makeFragmentName(int viewId, int index){
            return "android:switcher:" + viewId + ":" + index;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Fragment fragment = (Fragment) super.instantiateItem(container, position);
            registeredFragments.put(position, fragment);
            return fragment;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            registeredFragments.remove(position);
            super.destroyItem(container, position, object);
        }

        public Fragment getRegisteredFragment(int position) {
            return registeredFragments.get(position);
        }

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            Log.i(TAG, "Tab Selection : " + position );
            switch(position){
                case 0:
                    Fragment login = new LogInFragment();
                    return login;
                case 1:
                    Fragment viewall = new ViewAllFragment();
                    return viewall;
                case 2:
                    Fragment single = new SingleStreamFragment();
                    return single;
                case 3:
                    Fragment nearBy = new NearByFragment();
                    return nearBy;
            }
            return null;

        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section4).toUpperCase(l);
                case 3:
                    return getString(R.string.title_section3).toUpperCase(l);
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        public static final String TAG = "PlaceholderFragment";
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            Log.i(TAG, "PlaceholderFragment" );
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

}
