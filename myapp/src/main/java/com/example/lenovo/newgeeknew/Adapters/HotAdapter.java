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
import com.example.lenovo.newgeeknew.Beens.HotBeen;
import com.example.lenovo.newgeeknew.Beens.SectionsBeen;
import com.example.lenovo.newgeeknew.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * @author: 汐er
 * @QQ: 2044273990
 * @date: 2019/4/17
 * @Week: 星期三
 * @GitHub: https://github.com/HanXier
 */
public class HotAdapter extends RecyclerView.Adapter{



    Context context;
    List<HotBeen.RecentBean> list;

    public HotAdapter(Context context, List <HotBeen.RecentBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolderONE(LayoutInflater.from(context).inflate(R.layout.item_hot,null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        MyViewHolderONE myViewHolderONE = (MyViewHolderONE) viewHolder;
        HotBeen.RecentBean recentBean = list.get(i);
        myViewHolderONE.theme_kind.setText(recentBean.getTitle());


        Glide.with(context).load(recentBean.getThumbnail()).into(myViewHolderONE.about);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolderONE extends XRecyclerView.ViewHolder{

        private ImageView about;
        private TextView theme_kind;


        public MyViewHolderONE(@NonNull View itemView) {
            super(itemView);


            about = itemView.findViewById(R.id.theme_bg);

            theme_kind = itemView.findViewById(R.id.theme_kind);
        }
    }
}
