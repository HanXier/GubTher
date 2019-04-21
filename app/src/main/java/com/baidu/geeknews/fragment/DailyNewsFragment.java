package com.baidu.geeknews.fragment;

import android.support.v4.app.ActivityCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.baidu.geeknews.R;
import com.baidu.geeknews.adapter.RlvDailyNewsAdapter;
import com.baidu.geeknews.base.BaseFragment;
import com.baidu.geeknews.bean.DailyNewsBean;
import com.baidu.geeknews.presenter.DailyNewsP;
import com.baidu.geeknews.presenter.EmptyP;
import com.baidu.geeknews.view.DailyNewsV;
import com.baidu.geeknews.view.EmptyV;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * @author xts
 *         Created by asus on 2019/4/3.
 */

public class DailyNewsFragment extends BaseFragment<DailyNewsV,DailyNewsP>
    implements DailyNewsV{
    private static final String TAG = "DailyNewsFragment";
    @BindView(R.id.rlv)
    RecyclerView mRlv;
    private RlvDailyNewsAdapter mAdapter;

    @Override
    protected DailyNewsP initPresenter() {
        return new DailyNewsP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_daily_news;
    }

    @Override
    protected void initView() {
        mRlv.setLayoutManager(new LinearLayoutManager(getContext()));
        ArrayList<DailyNewsBean.StoriesBean> storiesBeans = new ArrayList<>();
        ArrayList<DailyNewsBean.TopStoriesBean> topStoriesBeans = new ArrayList<>();
        mAdapter = new RlvDailyNewsAdapter(getContext(),topStoriesBeans,storiesBeans);
        mRlv.setAdapter(mAdapter);

        //mRlv.addItemDecoration();
    }

    @Override
    protected void initData() {
        mPresenter.getData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach: ");
    }

    @Override
    public void setData(DailyNewsBean bean) {
        mAdapter.setData(bean);
    }
}
