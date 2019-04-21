package com.example.lenovo.newgeeknew.Adapters;


import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.lenovo.newgeeknew.Beens.GoldTitleBean;
import com.example.lenovo.newgeeknew.GoldPageFragments.GoldPagerFragment;

import java.util.ArrayList;

/**
 * @author: 汐er
 * @QQ: 2044273990
 * @date: 2019/4/18
 * @Week: 星期四
 * @GitHub: https://github.com/HanXier
 */
public class GoldAdapter extends FragmentStatePagerAdapter {
    private ArrayList<GoldPagerFragment> mFragments;
    private ArrayList<GoldTitleBean> mTitles;
    private ArrayList<String> mNewTitles = new ArrayList<>();

    public GoldAdapter(FragmentManager fm, ArrayList <GoldPagerFragment> mFragments, ArrayList <GoldTitleBean> mTitles) {
        super(fm);
        this.mFragments = mFragments;
        this.mTitles = mTitles;


        for (int i = 0; i < mTitles.size(); i++) {
            GoldTitleBean bean = mTitles.get(i);
            if (bean.isChecked){
                mNewTitles.add(bean.title);
            }
        }
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mNewTitles.get(position);
    }
}
