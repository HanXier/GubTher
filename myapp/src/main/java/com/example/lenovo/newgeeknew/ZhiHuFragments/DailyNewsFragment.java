package com.example.lenovo.newgeeknew.ZhiHuFragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lenovo.newgeeknew.Activitys.CalendarActivity;
import com.example.lenovo.newgeeknew.Adapters.DailyNewsAdapter;
import com.example.lenovo.newgeeknew.Beens.DailyNewsBean;
import com.example.lenovo.newgeeknew.MyService;
import com.example.lenovo.newgeeknew.R;
import com.example.lenovo.newgeeknew.Utils.CircularAnimUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class DailyNewsFragment extends Fragment implements View.OnClickListener {


    private View view;
    private RecyclerView mRlv;
    //private String s ;
    private List <DailyNewsBean.StoriesBean> storiesBeanList = new ArrayList <>();
    private List <DailyNewsBean.TopStoriesBean> topStoriesBeanList = new ArrayList <>();
    private DailyNewsAdapter adapter;
    private FloatingActionButton mFabCalender;

    public DailyNewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_daily_news, container, false);
        initView(view);
        initretrofit();

        return view;
    }



    private void initView(View view) {
        mFabCalender = (FloatingActionButton) view.findViewById(R.id.fab_calender);
        mRlv = (RecyclerView) view.findViewById(R.id.rlv);
        adapter = new DailyNewsAdapter(getActivity(), storiesBeanList, topStoriesBeanList);
        mRlv.setAdapter(adapter);
        mRlv.setLayoutManager(new LinearLayoutManager(getActivity()));


        mFabCalender.setOnClickListener(this);
    }

    private void initretrofit() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(MyService.sBaseUrl).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();

        MyService myService = retrofit.create(MyService.class);
        // Map<String,Object> map=new HashMap<>();

        Observable <DailyNewsBean> data = myService.getLastDailyNews();

        data.subscribeOn(Schedulers.io())

                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer <DailyNewsBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(DailyNewsBean tab) {
                adapter.setData(tab);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.fab_calender:
                Intent it = new Intent();
                it.setClass(getActivity(),CalendarActivity.class);
                CircularAnimUtil.startActivity(getActivity(),it,mFabCalender,R.color.fab_bg);
                break;
        }
    }

}
