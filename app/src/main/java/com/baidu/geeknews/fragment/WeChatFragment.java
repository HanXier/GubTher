package com.baidu.geeknews.fragment;

import com.baidu.geeknews.R;
import com.baidu.geeknews.base.BaseFragment;
import com.baidu.geeknews.presenter.WeChatP;
import com.baidu.geeknews.presenter.ZhihuDailyNewsP;
import com.baidu.geeknews.view.WeChatV;
import com.baidu.geeknews.view.ZhihuDailyNewsV;

/**
 * @author xts
 *         Created by asus on 2019/4/3.
 */

public class WeChatFragment extends BaseFragment<WeChatV,WeChatP>
    implements WeChatV{
    @Override
    protected WeChatP initPresenter() {
        return new WeChatP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wechat;
    }
}
