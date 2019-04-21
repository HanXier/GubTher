package com.example.lenovo.newgeeknew.GoldPageFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.newgeeknew.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GoldPagerFragment extends Fragment {


    public GoldPagerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_gold_pager, container, false);
        return inflate;



    }

    public static GoldPagerFragment newInstance(String title) {
        GoldPagerFragment goldDetailFragment = new GoldPagerFragment();
        Bundle bundle = new Bundle();
        bundle.putString("data",title);
        goldDetailFragment.setArguments(bundle);
        return goldDetailFragment;
    }
}
