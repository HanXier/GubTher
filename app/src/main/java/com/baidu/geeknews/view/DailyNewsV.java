package com.baidu.geeknews.view;

import com.baidu.geeknews.base.BaseMvpView;
import com.baidu.geeknews.bean.DailyNewsBean;

/**
 * @author xts
 *         Created by asus on 2019/4/3.
 */

public interface DailyNewsV extends BaseMvpView {
    void setData(DailyNewsBean bean);
}
