package com.example.lenovo.newgeeknew.Adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lenovo.newgeeknew.Beens.DailyNewsBean;
import com.example.lenovo.newgeeknew.R;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: 汐er
 * @QQ: 2044273990
 * @date: 2019/4/17
 * @Week: 星期三
 * @GitHub: https://github.com/HanXier
 */
public class DailyNewsAdapter extends RecyclerView.Adapter{
    Context context;

    List<DailyNewsBean.StoriesBean>storiesBeanList;
    List<DailyNewsBean.TopStoriesBean>topStoriesBeanList;
    private static final int TYPE_BANNER = 0;
    private static final int TYPE_TIME = 1;
    private static final int TYPE_NEWS = 2;
    private String mDate;

    public DailyNewsAdapter(Context context, List <DailyNewsBean.StoriesBean> storiesBeanList, List <DailyNewsBean.TopStoriesBean> topStoriesBeanList) {
        this.context = context;
        this.storiesBeanList = storiesBeanList;
        this.topStoriesBeanList = topStoriesBeanList;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        if (i == TYPE_BANNER){
            //banner
            return new BannerVH(inflater.inflate(R.layout.item_banner,null));
        }else if (i == TYPE_TIME){
            //日期
            return new TimeVH(inflater.inflate(R.layout.item_text,null));
        }else {
            //新闻
            return new NewsVH(inflater.inflate(R.layout.item_item,null));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        int viewType = getItemViewType(i);
        if (viewType == TYPE_BANNER){
            //banner
            BannerVH bannerVH = (BannerVH) viewHolder;
            bannerVH.mBanner.setImages(topStoriesBeanList)
                    .setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object path,
                                                 ImageView imageView) {
                            DailyNewsBean.TopStoriesBean bean = (DailyNewsBean.TopStoriesBean) path;
                            Glide.with(context).load(bean.getImage()).into(imageView);
                        }
                    })
                    .start();
        }else if (viewType == TYPE_TIME){
            //日期

            TimeVH timeVH = (TimeVH) viewHolder;
           timeVH.mTvTime.setText(mDate);
        }else {
            //新闻
            int newPostion = i-1;
            if (topStoriesBeanList.size()>0){
                newPostion -= 1;
            }
            DailyNewsBean.StoriesBean storiesBean = storiesBeanList.get(newPostion);
            List <String> images = storiesBean.getImages();
            String s = images.get(0);
            NewsVH newsVH = (NewsVH) viewHolder;
            newsVH.mTvTime.setText(storiesBean.getTitle());
            Glide.with(context).load(s).into(newsVH.minage);
        }
    }

    @Override
    public int getItemCount() {

        if (topStoriesBeanList.size()>0){
            return 1+1+storiesBeanList.size();
        }else {
            return 1+storiesBeanList.size();
        }
    }

    @Override
    public int getItemViewType(int position) {

        if (topStoriesBeanList.size()>0){
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
    public void setData(DailyNewsBean bean) {
        mDate = bean.getDate();
        topStoriesBeanList.clear();
        if (bean.getTop_stories() != null && bean.getTop_stories().size()>0){
            topStoriesBeanList.addAll(bean.getTop_stories());
        }

        storiesBeanList.clear();
        if (bean.getStories() != null && bean.getStories().size()>0){
            storiesBeanList.addAll(bean.getStories());
        }

        notifyDataSetChanged();
    }
    class BannerVH extends RecyclerView.ViewHolder{

        Banner mBanner;
        public BannerVH(View itemView) {
            super(itemView);
            mBanner=itemView.findViewById(R.id.banner);
        }
    }

    class TimeVH extends RecyclerView.ViewHolder{

        TextView mTvTime;
        public TimeVH(View itemView) {
            super(itemView);
            mTvTime=  itemView.findViewById(R.id.tv_time);
        }
    }

    class NewsVH extends RecyclerView.ViewHolder{

        TextView mTvTime;
        ImageView minage;
        public NewsVH(View itemView) {
            super(itemView);
            mTvTime=  itemView.findViewById(R.id.tv_daily_item_title);
            minage=  itemView.findViewById(R.id.iv_daily_item_image);
        }
    }
}
