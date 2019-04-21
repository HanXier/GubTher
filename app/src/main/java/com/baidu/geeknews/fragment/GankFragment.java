package com.baidu.geeknews.fragment;

import com.baidu.geeknews.R;
import com.baidu.geeknews.base.BaseFragment;
import com.baidu.geeknews.presenter.GankP;
import com.baidu.geeknews.presenter.WeChatP;
import com.baidu.geeknews.view.GankV;
import com.baidu.geeknews.view.WeChatV;

/**
 * @author xts
 *         Created by asus on 2019/4/3.
 */

public class GankFragment extends BaseFragment<GankV,GankP>
    implements GankV{
    @Override
    protected GankP initPresenter() {
        return new GankP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gank;
    }
}
