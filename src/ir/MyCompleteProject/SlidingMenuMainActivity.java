package ir.MyCompleteProject;

import ir.SlidingMenu.SlidingMenuNavDrawerItem;
import ir.SlidingMenu.SlidingMenuNavDrawerListAdapter;
import java.util.ArrayList;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class SlidingMenuMainActivity extends ProjectActivityEnhanced {

    private DrawerLayout                        mDrawerLayout;
    private ListView                            mDrawerList;
    private ActionBarDrawerToggle               mDrawerToggle;

    // nav drawer title
    private CharSequence                        mDrawerTitle;

    // used to store app title
    private CharSequence                        mTitle;

    // slide menu items
    private String[]                            navMenuTitles;
    private TypedArray                          navMenuIcons;

    private ArrayList<SlidingMenuNavDrawerItem> navDrawerItems;
    private SlidingMenuNavDrawerListAdapter     adapter;


    /////////////////////////  make actionbar right to left !  /////////////////////
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void forceRTLIfSupported()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }
    }


    /////////////////////////  make actionbar right to left !  /////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sliding_menu);

        ActionBar actionBar = getActionBar();

        // Changing the action bar icon
        actionBar.setIcon(R.drawable.ico_actionbar);

        // Hide the action bar title
        actionBar.setDisplayShowTitleEnabled(true);

        // Enabling Back navigation on Action Bar icon
        actionBar.setDisplayHomeAsUpEnabled(false);

        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff0000")));

        ///////////////////////////////////////////////////////////////////////////////////         

        forceRTLIfSupported();

        mTitle = mDrawerTitle = getTitle();

        // load slide menu items
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

        // nav drawer icons from resources
        navMenuIcons = getResources()
                .obtainTypedArray(R.array.nav_drawer_icons);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

        navDrawerItems = new ArrayList<SlidingMenuNavDrawerItem>();

        // adding nav drawer items to array
        // Home
        navDrawerItems.add(new SlidingMenuNavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
        // Find People
        navDrawerItems.add(new SlidingMenuNavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
        // Photos
        navDrawerItems.add(new SlidingMenuNavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
        // Communities, Will add a counter here
        navDrawerItems.add(new SlidingMenuNavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1), true, "22"));
        // Pages
        navDrawerItems.add(new SlidingMenuNavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
        // What's hot, We  will add a counter here
        navDrawerItems.add(new SlidingMenuNavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1), true, "50+"));

        navDrawerItems.add(new SlidingMenuNavDrawerItem(navMenuTitles[6], navMenuIcons.getResourceId(6, -1)));
        // Find People
        navDrawerItems.add(new SlidingMenuNavDrawerItem(navMenuTitles[7], navMenuIcons.getResourceId(7, -1)));
        // Photos
        navDrawerItems.add(new SlidingMenuNavDrawerItem(navMenuTitles[8], navMenuIcons.getResourceId(8, -1)));
        // Communities, Will add a counter here
        navDrawerItems.add(new SlidingMenuNavDrawerItem(navMenuTitles[9], navMenuIcons.getResourceId(9, -1), true, "22"));
        // Pages
        navDrawerItems.add(new SlidingMenuNavDrawerItem(navMenuTitles[10], navMenuIcons.getResourceId(10, -1)));
        // What's hot, We  will add a counter here
        navDrawerItems.add(new SlidingMenuNavDrawerItem(navMenuTitles[11], navMenuIcons.getResourceId(11, -1), true, "50+"));

        // Recycle the typed array
        navMenuIcons.recycle();

        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

        // setting the nav drawer list adapter
        adapter = new SlidingMenuNavDrawerListAdapter(getApplicationContext(),
                navDrawerItems);
        mDrawerList.setAdapter(adapter);

        ///////////////
        ///////////////
        ///////////////

        // enabling action bar app icon and behaving it as toggle button
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_white_drawer, //nav menu toggle icon
                R.string.app_name, // nav drawer open - description for accessibility
                R.string.app_name // nav drawer close - description for accessibility
        ) {

            @Override
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                // calling onPrepareOptionsMenu() to show action bar icons
                invalidateOptionsMenu();
            }


            @Override
            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                // calling onPrepareOptionsMenu() to hide action bar icons
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            // on first time display view for first nav item
            displayView(0);
        }
    }


    /**
     * Slide menu item click listener
     * */
    private class SlideMenuClickListener implements
            ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            // display view for selected nav drawer item
            displayView(position);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.actionbar_main_actions, menu);

        /*        // Associate searchable configuration with the SearchView

                SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

                SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();

                searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));*/

        /*        ///////////////////// make ActionBar Right to left by  ActionBarRtlizer/////////////////////////
                ActionBarRtlizer rtlizer = new ActionBarRtlizer(this);
                ViewGroup homeView = (ViewGroup) rtlizer.getHomeView();

                RtlizeEverything.rtlize(rtlizer.getActionBarView());

                if (rtlizer.getHomeViewContainer() instanceof ViewGroup) {
                    RtlizeEverything.rtlize((ViewGroup) rtlizer.getHomeViewContainer());
                }

                RtlizeEverything.rtlize(homeView);
                rtlizer.flipActionBarUpIconIfAvailable(homeView);
                ///////////////////// make ActionBar Right to left by  ActionBarRtlizer/////////////////////////        
        */return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // toggle nav drawer on selecting action bar app icon/title
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action bar actions click
        switch (item.getItemId()) {
        //            case R.id.action_settings:
        //                return true;

            case R.id.action_search:
                // search action
                return true;

            case R.id.action_location_found:
                // location found
                LocationFound();
                return true;

            case R.id.action_refresh:
                // refresh

                return true;

            case R.id.action_help:
                // help action

                Intent i0 = new Intent(SlidingMenuMainActivity.this, TabsSwipeGestureMainActivity.class);
                startActivity(i0);

                return true;

            case R.id.action_check_updates:
                // check for updates action

                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }


    /* *
     * Called when invalidateOptionsMenu() is triggered
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // if nav drawer is opened, hide the action items
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_settings).setVisible( !drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }


    /**
     * Diplaying fragment view for selected nav drawer list item
     * */
    private void displayView(int position) {
        // update the main content by replacing fragments
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new SlidingMenuHomeFragment();
                break;
            case 1:
                fragment = new SlidingMenuFindPeopleFragment();
                break;
            case 2:
                fragment = new SlidingMenuPhotosFragment();
                break;
            case 3:
                fragment = new SlidingMenuCommunityFragment();
                break;
            case 4:
                fragment = new SlidingMenuPagesFragment();
                break;
            case 5:
                fragment = new SlidingMenuWhatsHotFragment();
                break;

            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, fragment).commit();

            // update selected item and title, then close the drawer
            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            setTitle(navMenuTitles[position]);
            mDrawerLayout.closeDrawer(mDrawerList);
        } else {
            // error in creating fragment
            Log.e("MainActivity", "Error in creating fragment");
        }
    }


    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }


    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


    /**
     * Launching new activity
     * */
    private void LocationFound() {
        Intent i3 = new Intent(SlidingMenuMainActivity.this, ActionBarLocationFound.class);
        startActivity(i3);
    }

}

///
///
//
//
//

//
//

////////////////   right to left sliding menu ! //////////////////// 

/*        In your main layout set your ListView gravity to right:

            android:layout_gravity="right" 
            Also in your code :

            mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                        R.drawable.ic_drawer, R.string.drawer_open,
                        R.string.drawer_close) {

                @Override
                public boolean onOptionsItemSelected(MenuItem item) {
                    if (item != null && item.getItemId() == android.R.id.home) {
                        if (mDrawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                            mDrawerLayout.closeDrawer(Gravity.RIGHT);
                        } 
                        else {
                            mDrawerLayout.openDrawer(Gravity.RIGHT);
                        }
                    }
                    return false;
                }
            }; */

////////////////   right to left sliding menu ! //////////////////// 

////////////////  make application right to left ! ////////////////////
/*        Add this code to manifest:

            <application android:supportsRtl="true">*/

//getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

////////////////  make application right to left ! ////////////////////  

