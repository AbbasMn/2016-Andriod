package ir.TabsSwipeGesture;

import ir.MyCompleteProject.TabsSwipeGesturePhotosFragment;
import ir.MyCompleteProject.TabsSwipeGestureSongFragment;
import ir.MyCompleteProject.TabsSwipeGestureVideoFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class TabsSwipeGesturePagerAdapter extends FragmentPagerAdapter {

    public TabsSwipeGesturePagerAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                // Photo fragment activity
                return new TabsSwipeGesturePhotosFragment();
            case 1:
                // Song fragment activity
                return new TabsSwipeGestureSongFragment();
            case 2:
                // Vedio fragment activity
                return new TabsSwipeGestureVideoFragment();
        }

        return null;
    }


    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 3;
    }

}
