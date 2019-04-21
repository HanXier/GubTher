package com.example.lenovo.newgeeknew.ZhiHuFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.newgeeknew.Adapters.HotAdapter;
import com.example.lenovo.newgeeknew.Beens.HotBeen;
import com.example.lenovo.newgeeknew.MyService;
import com.example.lenovo.newgeeknew.R;

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
public class HotFragment extends Fragment {


    private View view;
    private RecyclerView mRecy;
    private List<HotBeen.RecentBean>list = new ArrayList <>();
    private HotAdapter adapter;

    public HotFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hot, container, false);
        initView(view);
        initretrofit();
        return view;

    }

    private void initView(View view) {
        mRecy = (RecyclerView) view.findViewById(R.id.recy);
        adapter = new HotAdapter(getActivity(), list);
        mRecy.setAdapter(adapter);
        mRecy.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void initretrofit() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(MyService.sBaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

            MyService myService = retrofit.create(MyService.class);
           // Map<String,Object> map=new HashMap<>();
           // map.put("appKey","72ed8f3327d246da8d85fc07ef423fe1");
            Observable<HotBeen> data = myService.GetHot( );

            data.subscribeOn(Schedulers.io())

                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<HotBeen>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(HotBeen tab) {
                            list.addAll(tab.getRecent());
                            adapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });


        }

}
