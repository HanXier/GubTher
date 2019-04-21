package com.example.lenovo.newgeeknew.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.example.lenovo.newgeeknew.Adapters.DailyAdapter;
import com.example.lenovo.newgeeknew.Beens.GoldTitleBean;
import com.example.lenovo.newgeeknew.R;
import com.example.lenovo.newgeeknew.Utils.SimpleItemTouchCallBack;

import java.util.ArrayList;

public class GoldManagerActivity extends AppCompatActivity {

    private Toolbar mToolBar;
    private RecyclerView mRvGoldManagerList;
    private ArrayList<GoldTitleBean> mTitles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gold_manager);
        initView();
    }
    //08A 刘清华
    private void initView() {
        mToolBar = (Toolbar) findViewById(R.id.tool_bar);
        mRvGoldManagerList = (RecyclerView) findViewById(R.id.rv_gold_manager_list);


        mToolBar.setTitle(R.string.special_show);
        mToolBar.setNavigationIcon(R.mipmap.ic_close);
        setSupportActionBar(mToolBar);
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAct();
            }
        });

        mTitles = (ArrayList<GoldTitleBean>) getIntent().getSerializableExtra(SyncStateContract.Constants.DATA);
        mRvGoldManagerList.setLayoutManager(new LinearLayoutManager(this));
        DailyAdapter adapter = new DailyAdapter(mTitles);
        mRvGoldManagerList.setAdapter(adapter);
        mRvGoldManagerList.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));


        //拖拽移动和左滑删除
        SimpleItemTouchCallBack simpleItemTouchCallBack = new SimpleItemTouchCallBack(adapter);
        simpleItemTouchCallBack.setSwipeEnable(false);
        ItemTouchHelper helper = new ItemTouchHelper(simpleItemTouchCallBack);
        helper.attachToRecyclerView(mRvGoldManagerList);

    }


    private void finishAct() {
        Intent intent = new Intent();
        intent.putExtra("data",mTitles);
        setResult(RESULT_OK,intent);
        finish();
    }
}
