package ir.MyCompleteProject;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class SlidingMenuCommunityFragment extends Fragment {

    public SlidingMenuCommunityFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.sliding_menu_fragment_community, container, false);

        return rootView;
    }
}
