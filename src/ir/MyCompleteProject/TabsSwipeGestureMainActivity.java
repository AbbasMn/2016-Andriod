package ir.MyCompleteProject;

import ir.ActionBar.ActionBarRtlizer;
import ir.ActionBar.RtlizeEverything;
import ir.TabsSwipeGesture.TabsSwipeGesturePagerAdapter;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.ViewGroup;


public class TabsSwipeGestureMainActivity extends FragmentActivity implements
        ActionBar.TabListener {

    private ViewPager                    viewPager;
    private TabsSwipeGesturePagerAdapter mAdapter;
    private ActionBar                    actionBar;
    // Tab titles
    private String[]                     tabs = { "Photos", "Song", "Video" };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabs_swipe_gesture_activity_main);

        // get the action bar
        actionBar = getActionBar();

        // Enabling Back navigation on Action Bar icon
        actionBar.setDisplayHomeAsUpEnabled(true);

        ///////////////////////////////////////////////////////////////////////////////////        

        // Initilization
        viewPager = (ViewPager) findViewById(R.id.pager);
        //actionBar = getActionBar();
        mAdapter = new TabsSwipeGesturePagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(mAdapter);
        //actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        int drwIcon = R.drawable.ic_home;
        // Adding Tabs
        for (String tab_name: tabs) {

            if (tab_name.equals("Video"))
                drwIcon = R.drawable.icon_videos_tab;
            else if (tab_name.equals("Song"))
                drwIcon = R.drawable.icon_songs_tab;
            else if (tab_name.equals("Photos"))
                drwIcon = R.drawable.icon_photos_tab;

            actionBar.addTab(actionBar.newTab()
                    .setText(tab_name)
                    .setIcon(getResources().getDrawable(drwIcon))
                    .setTabListener(this));

        }

        /**
         * on swiping the viewpager make respective tab selected
         * */
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                // on changing the page
                // make respected tab selected
                actionBar.setSelectedNavigationItem(position);
            }


            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {}


            @Override
            public void onPageScrollStateChanged(int arg0) {}
        });
    }


    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {}


    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        // on tab selected
        // show respected fragment view
        viewPager.setCurrentItem(tab.getPosition());
    }


    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

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
        return true;
    }

}
