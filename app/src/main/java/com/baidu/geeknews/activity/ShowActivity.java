package com.baidu.geeknews.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.baidu.geeknews.R;
import com.baidu.geeknews.adapter.RlvShowAdapter;
import com.baidu.geeknews.base.BaseActivity;
import com.baidu.geeknews.base.Constants;
import com.baidu.geeknews.bean.GoldTitleBean;
import com.baidu.geeknews.presenter.EmptyP;
import com.baidu.geeknews.view.EmptyV;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowActivity extends BaseActivity<EmptyV, EmptyP> implements EmptyV {

    @BindView(R.id.toolBar)
    Toolbar mToolBar;
    @BindView(R.id.rlv)
    RecyclerView mRlv;
    private ArrayList<GoldTitleBean> mTitles;

    @Override
    protected EmptyP initPresenter() {
        return new EmptyP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_show;
    }

    @Override
    protected void initView() {
        mToolBar.setTitle(R.string.special_show);
        mToolBar.setNavigationIcon(R.mipmap.ic_close);
        setSupportActionBar(mToolBar);
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAct();
            }
        });

        mTitles = (ArrayList<GoldTitleBean>) getIntent().getSerializableExtra(Constants.DATA);
        mRlv.setLayoutManager(new LinearLayoutManager(this));
        RlvShowAdapter adapter = new RlvShowAdapter(mTitles);
        mRlv.setAdapter(adapter);
        mRlv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
    }

    private void finishAct() {
        Intent intent = new Intent();
        intent.putExtra(Constants.DATA,mTitles);
        setResult(RESULT_OK,intent);
        finish();
    }
}
