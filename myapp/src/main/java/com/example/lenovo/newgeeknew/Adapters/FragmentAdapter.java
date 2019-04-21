package com.example.lenovo.newgeeknew.Adapters;


import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * @author: 汐er
 * @QQ: 2044273990
 * @date: 2019/4/17
 * @Week: 星期三
 * @GitHub: https://github.com/HanXier
 */
public class FragmentAdapter extends FragmentStatePagerAdapter{

     private List<Fragment> list;
         private String[] s;

         public FragmentAdapter(FragmentManager fm, List<Fragment> list, String[] s) {
             super(fm);
             this.list = list;
             this.s = s;
         }

         @Override
         public Fragment getItem(int i) {
             return list.get(i);
         }

         @Override
         public int getCount() {
             return list.size();
         }

         @Nullable
         @Override
         public CharSequence getPageTitle(int position) {
             return s[position];
         }
}
