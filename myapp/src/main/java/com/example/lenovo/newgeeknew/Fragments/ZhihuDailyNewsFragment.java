package com.example.lenovo.newgeeknew.Fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.newgeeknew.Adapters.FragmentAdapter;
import com.example.lenovo.newgeeknew.R;
import com.example.lenovo.newgeeknew.ZhiHuFragments.DailyNewsFragment;
import com.example.lenovo.newgeeknew.ZhiHuFragments.HotFragment;
import com.example.lenovo.newgeeknew.ZhiHuFragments.SectionsFragment;
import com.example.lenovo.newgeeknew.ZhiHuFragments.ThemeFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ZhihuDailyNewsFragment extends Fragment {


    private View view;
    private TabLayout mTabLayout;
    private ViewPager mVp;

    public ZhihuDailyNewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_zhihu_daily_news, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mTabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        mVp = (ViewPager) view.findViewById(R.id.vp);



        String [] s = {"日报","主题","专栏","热门"};

        List<Fragment>list = new ArrayList <>();


        list.add(new DailyNewsFragment());
        list.add(new ThemeFragment());
        list.add(new SectionsFragment());
        list.add(new HotFragment());

        FragmentAdapter fragmentAdapter = new FragmentAdapter(getActivity().getSupportFragmentManager(), list, s);

        mVp.setAdapter(fragmentAdapter);
        mTabLayout.setupWithViewPager(mVp);
    }

}
