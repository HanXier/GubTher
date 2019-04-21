package com.baidu.geeknews.presenter;

import com.baidu.geeknews.base.BasePresenter;
import com.baidu.geeknews.bean.DailyNewsBean;
import com.baidu.geeknews.model.DailyNewsM;
import com.baidu.geeknews.net.ResultCallBack;
import com.baidu.geeknews.view.DailyNewsV;
import com.baidu.geeknews.view.GankV;

/**
 * @author xts
 *         Created by asus on 2019/4/3.
 */

public class DailyNewsP extends BasePresenter<DailyNewsV> {

    private DailyNewsM mDailyNewsM;

    @Override
    protected void initModel() {
        mDailyNewsM = new DailyNewsM();
        mModels.add(mDailyNewsM);
    }

    public void getData() {
        mDailyNewsM.getData(new ResultCallBack<DailyNewsBean>() {
            @Override
            public void onSuccess(DailyNewsBean bean) {
                if (bean != null){
                    if (mMvpView != null){
                        mMvpView.setData(bean);
                    }
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
}
