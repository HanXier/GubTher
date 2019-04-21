package com.baidu.geeknews.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.baidu.geeknews.base.BaseFragment;

import java.util.ArrayList;

/**
 * @author xts
 *         Created by asus on 2019/4/17.
 *         生命周期不一样
 *         FragmentStatePagerAdapter:用不着的碎片生命周期,onDetach();取消关联
 *         FragmentPagerAdapter:用不着的碎片生命周期,只会走到onDestoryView();
 *
 */

public class VpZhihuAdapter extends FragmentStatePagerAdapter {
    private Context mContext;
    private ArrayList<BaseFragment> mFragments;
    private ArrayList<Integer> mTitles;

    public VpZhihuAdapter(Context context, FragmentManager fm,
                          ArrayList<BaseFragment> fragments, ArrayList<Integer> titles) {
        super(fm);
        mContext = context;
        mFragments = fragments;
        mTitles = titles;
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
        return mContext.getResources().getString(mTitles.get(position));
    }
}
