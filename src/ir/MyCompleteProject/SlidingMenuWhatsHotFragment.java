package ir.MyCompleteProject;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class SlidingMenuWhatsHotFragment extends Fragment {

    public SlidingMenuWhatsHotFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.sliding_menu_fragment_whats_hot, container, false);

        return rootView;
    }
}
