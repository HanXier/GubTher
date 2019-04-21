package com.example.lenovo.newgeeknew;


import com.example.lenovo.newgeeknew.Beens.DailyNewsBean;
import com.example.lenovo.newgeeknew.Beens.HotBeen;
import com.example.lenovo.newgeeknew.Beens.SectionsBeen;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author: 汐er
 * @QQ: 2044273990
 * @date: 2019/4/17
 * @Week: 星期三
 * @GitHub: https://github.com/HanXier
 */
public interface MyService {

    String sBaseUrl = "http://news-at.zhihu.com/api/4/";
    //hot
    @GET("news/latest")
    Observable<DailyNewsBean> getLastDailyNews();

    //hot
    @GET("sections")
    Observable<SectionsBeen> getSection();

    @GET("news/hot")
    Observable<HotBeen> GetHot();
}
