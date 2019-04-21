package com.example.lenovo.newgeeknew;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SearchEvent;
import android.widget.FrameLayout;

import com.example.lenovo.newgeeknew.Fragments.AboutFragment;
import com.example.lenovo.newgeeknew.Fragments.CollectFragment;
import com.example.lenovo.newgeeknew.Fragments.GankFragment;
import com.example.lenovo.newgeeknew.Fragments.GoldFragment;
import com.example.lenovo.newgeeknew.Fragments.SettingFragment;
import com.example.lenovo.newgeeknew.Fragments.V2exFragment;
import com.example.lenovo.newgeeknew.Fragments.WeChatFragment;
import com.example.lenovo.newgeeknew.Fragments.ZhihuDailyNewsFragment;
import com.example.lenovo.newgeeknew.Utils.Constants;
import com.example.lenovo.newgeeknew.Utils.RxBus;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, MaterialSearchView.OnQueryTextListener, MaterialSearchView.SearchViewListener {

    private Toolbar mToolBar;
    private FrameLayout mFragmentContainer;
    private NavigationView mNv;
    private DrawerLayout mDl;
    private ArrayList <Fragment> mFragments;
    private FragmentManager mManager;
    private ArrayList <Integer> mTitles;
    private final int TYPE_ZHIHU = 0;
    private final int TYPE_WECHAT = 1;
    private final int TYPE_GANK = 2;
    private final int TYPE_GOLD = 3;
    private final int TYPE_V2EX = 4;
    private final int TYPE_COLLECT = 5;
    private final int TYPE_SETTING = 6;
    private final int TYPE_ABOUT = 7;
    //上一次显示的fragmnet的索引
    private int mLastFragmentPosition = 0;
    private MaterialSearchView mViewSearch;
    private FrameLayout mToolbarContainer;
    private MenuItem mSearchMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        initTitles();

        initFragments();
        initPut();
    }

    private void initView() {
        mToolBar = (Toolbar) findViewById(R.id.toolBar);
        mFragmentContainer = (FrameLayout) findViewById(R.id.fragment_container);
        mNv = (NavigationView) findViewById(R.id.nv);
        mDl = (DrawerLayout) findViewById(R.id.dl);
        mViewSearch = (MaterialSearchView) findViewById(R.id.view_search);
        mToolbarContainer = (FrameLayout) findViewById(R.id.toolbar_container);

        mManager = getSupportFragmentManager();
        mToolBar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(mToolBar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDl, mToolBar, R.string.about, R.string.about);
        //设置旋转开关颜色
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        mDl.addDrawerListener(toggle);
        toggle.syncState();
        mNv.setNavigationItemSelectedListener(this);
        mViewSearch.setOnQueryTextListener(this);
        mViewSearch.setOnSearchViewListener(this);
    }


    private void initTitles() {
        mTitles = new ArrayList <>();
        mTitles.add(R.id.zhihu);
        mTitles.add(R.id.wechat);
        mTitles.add(R.id.gank);
        mTitles.add(R.id.gold);
        mTitles.add(R.id.v2ex);
        mTitles.add(R.id.collect);
        mTitles.add(R.id.settings);
        mTitles.add(R.id.about);
    }

    private void initFragments() {
        mFragments = new ArrayList <>();
        mFragments.add(new ZhihuDailyNewsFragment());
        mFragments.add(new WeChatFragment());
        mFragments.add(new GankFragment());
        mFragments.add(new GoldFragment());
        mFragments.add(new V2exFragment());
        mFragments.add(new CollectFragment());
        mFragments.add(new SettingFragment());
        mFragments.add(new AboutFragment());

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId != R.id.info_title && itemId != R.id.option_title) {
            menuItem.setChecked(true);
            switch (itemId) {
                case R.id.zhihu:
                    switchFragments(TYPE_ZHIHU);
                    break;
                case R.id.wechat:
                    switchFragments(TYPE_WECHAT);
                    break;
                case R.id.gank:
                    switchFragments(TYPE_GANK);
                    break;
                case R.id.gold:
                    switchFragments(TYPE_GOLD);
                    break;
                case R.id.v2ex:
                    switchFragments(TYPE_V2EX);
                    break;
                case R.id.collect:
                    switchFragments(TYPE_COLLECT);
                    break;
                case R.id.settings:
                    switchFragments(TYPE_SETTING);
                    break;
                case R.id.about:
                    switchFragments(TYPE_ABOUT);
                    break;
            }
            mDl.closeDrawer(Gravity.LEFT);
        } else {
            menuItem.setChecked(false);
        }
        return false;

    }

    private void switchFragments(int i) {

        //本质显示一个framgnet,隐藏一个
        //要显示的fragment
        Fragment fragment = mFragments.get(i);
        //要隐藏的碎片
        Fragment hideFragment = mFragments.get(mLastFragmentPosition);
        FragmentTransaction transaction = mManager.beginTransaction();
        if (!fragment.isAdded()) {
            transaction.add(R.id.fragment_container, fragment);
        }
        transaction.hide(hideFragment);
        transaction.show(fragment);
        transaction.commit();

        mLastFragmentPosition = i;

        if (i == TYPE_WECHAT|| i== TYPE_GANK){
            mSearchMenuItem.setVisible(true);
        }else {
            mSearchMenuItem.setVisible(false);
        }

        mToolBar.setTitle(mTitles.get(i));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        item.setVisible(false);
        mViewSearch.setMenuItem(item);
        mSearchMenuItem = item;
        return true;
    }

    private void initPut() {
        Fragment fragment = mFragments.get(0);
        FragmentManager manager = getSupportFragmentManager();

        FragmentTransaction transaction = manager.beginTransaction();

        transaction.add(R.id.fragment_container, fragment);
        transaction.commit();
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        //提交搜索内容时的监听

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        //搜索框展开
        return false;
    }

    @Override
    public void onSearchViewShown() {
        //打开
    }

    @Override
    public void onSearchViewClosed() {
        //关闭
    }
    /**
     * 按回退键会调用这个方法
     */
    @Override
    public void onBackPressed() {
        if (mViewSearch.isSearchOpen()) {
            mViewSearch.closeSearch();
        } else {
            super.onBackPressed();
        }
    }

}
