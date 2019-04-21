package com.baidu.geeknews.fragment;

import com.baidu.geeknews.R;
import com.baidu.geeknews.base.BaseFragment;
import com.baidu.geeknews.presenter.EmptyP;
import com.baidu.geeknews.view.EmptyV;

/**
 * @author xts
 *         Created by asus on 2019/4/3.
 */

public class AboutFragment extends BaseFragment<EmptyV,EmptyP>
    implements EmptyV{
    @Override
    protected EmptyP initPresenter() {
        return new EmptyP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_about;
    }
}
