package com.example.lenovo.newgeeknew.Fragments;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.lenovo.newgeeknew.Activitys.GoldManagerActivity;
import com.example.lenovo.newgeeknew.Adapters.GoldAdapter;
import com.example.lenovo.newgeeknew.Beens.GoldTitleBean;
import com.example.lenovo.newgeeknew.GoldPageFragments.GoldPagerFragment;
import com.example.lenovo.newgeeknew.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GoldFragment extends Fragment implements View.OnClickListener {


    private View view;
    private TabLayout mTabGoldMain;
    private ImageView mIvGoldMenu;
    private ViewPager mVpGoldMain;

    private ArrayList<GoldTitleBean> mTitles;
    private ArrayList<GoldPagerFragment> mFragments;
    private ArrayList<GoldTitleBean> mList;
    public GoldFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gold, container, false);
        initView(view);

        initTitles();
        setFragments();
        return view;
    }

    private void initView(View view) {
        mTabGoldMain = (TabLayout) view.findViewById(R.id.tab_gold_main);
        mIvGoldMenu = (ImageView) view.findViewById(R.id.iv_gold_menu);
        mIvGoldMenu.setOnClickListener(this);
        mVpGoldMain = (ViewPager) view.findViewById(R.id.vp_gold_main);

    }
    private void initTitles() {
        mTitles = new ArrayList<>();
        mTitles.add(new GoldTitleBean("Android",true));
        mTitles.add(new GoldTitleBean("iOS",true));
        mTitles.add(new GoldTitleBean("设计",true));
        mTitles.add(new GoldTitleBean("工具资源",true));
        mTitles.add(new GoldTitleBean("产品",true));
        mTitles.add(new GoldTitleBean("阅读",true));
        mTitles.add(new GoldTitleBean("前端",true));
        mTitles.add(new GoldTitleBean("后端",true));
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.iv_gold_menu:
                goActivity();
                break;
        }
    }

    //传值
    private void goActivity() {
        Intent intent = new Intent(getContext(), GoldManagerActivity.class);
        intent.putExtra("data",mTitles);
        startActivityForResult(intent,100);
    }


    //接收回传值
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null){
            if (requestCode == 100 && resultCode == Activity.RESULT_OK){
                mTitles = (ArrayList<GoldTitleBean>) data.getSerializableExtra("data");
                setFragments();
            }
        }
    }

    private void setFragments() {
        initFragments();
        GoldAdapter adapter = new GoldAdapter(getChildFragmentManager(), mFragments, mTitles);
        mVpGoldMain.setAdapter(adapter);
        mTabGoldMain.setupWithViewPager(mVpGoldMain);
    }

    private void initFragments() {
        mFragments = new ArrayList<>();
        for (int i = 0; i < mTitles.size(); i++) {
            GoldTitleBean goldTitleBean = mTitles.get(i);
            if (goldTitleBean.isChecked){
                mFragments.add(GoldPagerFragment.newInstance(goldTitleBean.title));
            }
        }
    }
}
