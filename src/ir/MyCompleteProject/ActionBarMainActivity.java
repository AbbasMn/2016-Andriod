package ir.MyCompleteProject;

import ir.ActionBar.ActionBarRtlizer;
import ir.ActionBar.ActionBarSpinnerNavItem;
import ir.ActionBar.ActionBarTitleNavigationAdapter;
import ir.ActionBar.RtlizeEverything;
import java.util.ArrayList;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.SearchView;


public class ActionBarMainActivity extends ProjectActivityEnhanced implements
        ActionBar.OnNavigationListener {

    // action bar
    private ActionBar                          actionBar;

    // Title navigation Spinner data
    private ArrayList<ActionBarSpinnerNavItem> navSpinner;

    // Navigation adapter
    private ActionBarTitleNavigationAdapter    adapter;

    // Refresh menu item
    private MenuItem                           refreshMenuItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getActionBar().hide();

        setContentView(R.layout.actionbar_main);

        getActionBar().show();

        ///////////////////////////////////////////////////////////////////////////////////////////////////////        

        Button btnSlidingMenu = (Button) findViewById(R.id.btnSlidingMenu);

        btnSlidingMenu.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                Intent intent = new Intent(getApplicationContext(), SlidingMenuMainActivity.class);
                startActivity(intent);

            }
        });

        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        Button btnTab = (Button) findViewById(R.id.btnTab);

        btnTab.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                Intent intent = new Intent(getApplicationContext(), TabsSwipeGestureMainActivity.class);
                startActivity(intent);

            }
        });

        ///////////////////////////////////////////////////////////////////////////////////////////////////////        

        /*        ActionBar bar = getActionBar();
                bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#E56E94")));
        */

        ///////////////////////////////////////////////////////////////////////////////////////////////////////  

        //forceRTLIfSupported();

        ///////////////////////////////////////////////////////////////////////////////////////////////////////          

        actionBar = getActionBar();

        //actionBar.setDisplayHomeAsUpEnabled(true);

        // Hide the action bar title
        actionBar.setDisplayShowTitleEnabled(false);

        // Changing the action bar icon
        actionBar.setIcon(R.drawable.ico_actionbar);

        // Enabling Spinner dropdown navigation
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

        // Spinner title navigation data
        navSpinner = new ArrayList<ActionBarSpinnerNavItem>();

        navSpinner.add(new ActionBarSpinnerNavItem("محل", R.drawable.ic_location));
        navSpinner
                .add(new ActionBarSpinnerNavItem("موقعیت ها", R.drawable.ic_my_places));
        navSpinner.add(new ActionBarSpinnerNavItem("چک لیست", R.drawable.ic_checkin));
        navSpinner.add(new ActionBarSpinnerNavItem("ارتفاع", R.drawable.ic_latitude));

        // title drop down adapter
        adapter = new ActionBarTitleNavigationAdapter(getApplicationContext(),
                navSpinner);

        // assigning the spinner navigation
        actionBar.setListNavigationCallbacks(adapter, this);

        // Changing the action bar icon
        // actionBar.setIcon(R.drawable.ico_actionbar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.actionbar_main_actions, menu);

        // Associate searchable configuration with the SearchView

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        ///////////////////// make ActionBar Right to left by  ActionBarRtlizer/////////////////////////
        ActionBarRtlizer rtlizer = new ActionBarRtlizer(this);
        ViewGroup homeView = (ViewGroup) rtlizer.getHomeView();

        RtlizeEverything.rtlize(rtlizer.getActionBarView());

        if (rtlizer.getHomeViewContainer() instanceof ViewGroup) {
            RtlizeEverything.rtlize((ViewGroup) rtlizer.getHomeViewContainer());
        }

        RtlizeEverything.rtlize(homeView);
        rtlizer.flipActionBarUpIconIfAvailable(homeView);
        ///////////////////// make ActionBar Right to left by  ActionBarRtlizer/////////////////////////

        return super.onCreateOptionsMenu(menu);
    }


    /**
     * On selecting action bar icons
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId())
        {

            case R.id.action_search:
                // search action
                return true;

            case R.id.action_location_found:
                // location found
                LocationFound();
                return true;

            case R.id.action_refresh:
                // refresh
                refreshMenuItem = item;
                // load the data from server
                new SyncData().execute();
                return true;

            case R.id.action_help:
                // help action

                Intent i0 = new Intent(ActionBarMainActivity.this, TabsSwipeGestureMainActivity.class);
                startActivity(i0);

                return true;

            case R.id.action_check_updates:
                // check for updates action

                Intent i2 = new Intent(ActionBarMainActivity.this, SlidingMenuMainActivity.class);
                startActivity(i2);

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    /**
     * Launching new activity
     * */
    private void LocationFound() {
        Intent i3 = new Intent(ActionBarMainActivity.this, ActionBarLocationFound.class);
        startActivity(i3);
    }


    /*
     * Actionbar navigation item select listener
     */
    @Override
    public boolean onNavigationItemSelected(int itemPosition, long itemId) {
        // Action to be taken after selecting a spinner item
        return false;
    }


    /**
     * Async task to load the data from server
     * **/
    private class SyncData extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {

            // set the progress bar view
            refreshMenuItem.setActionView(R.layout.actionbar_progressbar);

            refreshMenuItem.expandActionView();
        }


        @Override
        protected String doInBackground(String... params) {
            // not making real request in this demo
            // for now we use a timer to wait for sometime
            try {
                Thread.sleep(3000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(String result) {
            refreshMenuItem.collapseActionView();
            // remove the progress bar view
            refreshMenuItem.setActionView(null);
        }
    };


    /////////////////////////  make actionbar right to left !  /////////////////////

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void forceRTLIfSupported()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }
    }

    /////////////////////////  make actionbar right to left !  /////////////////////

}
