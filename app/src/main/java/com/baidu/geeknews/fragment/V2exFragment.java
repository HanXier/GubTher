package com.baidu.geeknews.fragment;

import com.baidu.geeknews.R;
import com.baidu.geeknews.base.BaseFragment;
import com.baidu.geeknews.presenter.GankP;
import com.baidu.geeknews.presenter.V2exP;
import com.baidu.geeknews.view.GankV;
import com.baidu.geeknews.view.V2exV;

/**
 * @author xts
 *         Created by asus on 2019/4/3.
 */

public class V2exFragment extends BaseFragment<V2exV,V2exP>
    implements V2exV{
    @Override
    protected V2exP initPresenter() {
        return new V2exP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_v2ex;
    }
}
