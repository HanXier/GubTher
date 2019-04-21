package com.baidu.geeknews.view;

import com.baidu.geeknews.base.BaseMvpView;

/**
 * @author xts
 *         Created by asus on 2019/4/2.
 */

public interface LoginView extends BaseMvpView {
    void setData(String data);

    String getUserName();
    String getPsd();

    void showToast(String msg);
}
