package com.baidu.geeknews.fragment;

import android.os.Bundle;
import android.widget.TextView;

import com.baidu.geeknews.R;
import com.baidu.geeknews.base.BaseFragment;
import com.baidu.geeknews.base.Constants;
import com.baidu.geeknews.presenter.EmptyP;
import com.baidu.geeknews.view.EmptyV;

import butterknife.BindView;

/**
 * @author xts
 *         Created by asus on 2019/4/3.
 */

public class GoldDetailFragment extends BaseFragment<EmptyV,EmptyP>
    implements EmptyV{
    @BindView(R.id.tv)
    TextView mTv;

    public static GoldDetailFragment newInstance(String text){
        GoldDetailFragment goldDetailFragment = new GoldDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.DATA,text);
        goldDetailFragment.setArguments(bundle);
        return goldDetailFragment;
    }

    @Override
    protected EmptyP initPresenter() {
        return new EmptyP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gold_detail;
    }

    @Override
    protected void initView() {
        Bundle arguments = getArguments();
        String data = arguments.getString(Constants.DATA);
        mTv.setText(data);
    }
}
