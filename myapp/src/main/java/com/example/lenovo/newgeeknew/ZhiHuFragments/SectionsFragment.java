package com.example.lenovo.newgeeknew.ZhiHuFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.newgeeknew.Adapters.SectionsAdapter;
import com.example.lenovo.newgeeknew.Beens.SectionsBeen;
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
public class SectionsFragment extends Fragment {


    private View view;
    private RecyclerView mRecy;
    private List<SectionsBeen.DataBean>list = new ArrayList();
    private SectionsAdapter sectionsAdapter;

    public SectionsFragment() {
        // Required empty public constructor
    }

    //刘清华 08a
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sections, container, false);
        initView(view);
        initretrofit();
        return view;
    }

    private void initView(View view) {
        mRecy = (RecyclerView) view.findViewById(R.id.recy);
        sectionsAdapter = new SectionsAdapter(getActivity(), list);
        mRecy.setAdapter(sectionsAdapter);
        mRecy.setLayoutManager(new GridLayoutManager(getActivity(),2));


    }

    private void initretrofit() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(MyService.sBaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

            MyService myService = retrofit.create(MyService.class);

            Observable<SectionsBeen> data = myService.getSection();

            data.subscribeOn(Schedulers.io())

                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<SectionsBeen>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(SectionsBeen tab) {
                            list.addAll(tab.getData());
                            sectionsAdapter.notifyDataSetChanged();
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
