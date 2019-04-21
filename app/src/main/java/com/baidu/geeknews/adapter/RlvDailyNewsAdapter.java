package com.baidu.geeknews.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.geeknews.R;
import com.baidu.geeknews.bean.DailyNewsBean;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.security.spec.EllipticCurve;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author xts
 *         Created by asus on 2019/4/17.
 */

public class RlvDailyNewsAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private ArrayList<DailyNewsBean.TopStoriesBean> mTopStoriesBeans;
    private ArrayList<DailyNewsBean.StoriesBean> mStoriesBeans;
    private static final int TYPE_BANNER = 0;
    private static final int TYPE_TIME = 1;
    private static final int TYPE_NEWS = 2;
    private String mDate = "今日要闻";

    public RlvDailyNewsAdapter(Context context,
                               ArrayList<DailyNewsBean.TopStoriesBean> topStoriesBeans,
                               ArrayList<DailyNewsBean.StoriesBean> storiesBeans) {

        mContext = context;
        mTopStoriesBeans = topStoriesBeans;
        mStoriesBeans = storiesBeans;
    }

    @Override
    public int getItemViewType(int position) {
        if (mTopStoriesBeans.size()>0){
            if (position == 0){
                return TYPE_BANNER;
            }else if (position == 1){
                return TYPE_TIME;
            }else {
                return TYPE_NEWS;
            }
        }else {
            if (position == 0){
                return TYPE_TIME;
            }else {
                return TYPE_NEWS;
            }
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        if (viewType == TYPE_BANNER){
            //banner
            return new BannerVH(inflater.inflate(R.layout.item_banner,null));
        }else if (viewType == TYPE_TIME){
            //日期
            return new TimeVH(inflater.inflate(R.layout.item_time,null));
        }else {
            //新闻
            return new NewsVH(inflater.inflate(R.layout.item_time,null));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (viewType == TYPE_BANNER){
            //banner
            BannerVH bannerVH = (BannerVH) holder;
            bannerVH.mBanner.setImages(mTopStoriesBeans)
                    .setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object path,
                                                 ImageView imageView) {
                            DailyNewsBean.TopStoriesBean bean = (DailyNewsBean.TopStoriesBean) path;
                            Glide.with(mContext).load(bean.getImage()).into(imageView);
                        }
                    })
                    .start();
        }else if (viewType == TYPE_TIME){
            //日期
            TimeVH timeVH = (TimeVH) holder;
            timeVH.mTvTime.setText(mDate);
        }else {
            //新闻
            int newPostion = position-1;
            if (mTopStoriesBeans.size()>0){
                newPostion -= 1;
            }
            DailyNewsBean.StoriesBean storiesBean = mStoriesBeans.get(newPostion);
            NewsVH newsVH = (NewsVH) holder;
            newsVH.mTvTime.setText(storiesBean.getTitle());
        }
    }

    @Override
    public int getItemCount() {
        if (mTopStoriesBeans.size()>0){
            return 1+1+mStoriesBeans.size();
        }else {
            return 1+mStoriesBeans.size();
        }
    }

    public void setData(DailyNewsBean bean) {
        mDate = bean.getDate();
        mTopStoriesBeans.clear();
        if (bean.getTop_stories() != null && bean.getTop_stories().size()>0){
            mTopStoriesBeans.addAll(bean.getTop_stories());
        }

        mStoriesBeans.clear();
        if (bean.getStories() != null && bean.getStories().size()>0){
            mStoriesBeans.addAll(bean.getStories());
        }

        notifyDataSetChanged();
        //notifyItemInserted();
    }

    class BannerVH extends RecyclerView.ViewHolder{
        @BindView(R.id.banner)
        Banner mBanner;
        public BannerVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    class TimeVH extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_time)
        TextView mTvTime;
        public TimeVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    class NewsVH extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_time)
        TextView mTvTime;
        public NewsVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
