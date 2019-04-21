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
import com.bumptech.glide.request.RequestOptions;
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
public class SectionsAdapter extends RecyclerView.Adapter{


    Context context;
    List<SectionsBeen.DataBean> list;

    public SectionsAdapter(Context context, List <SectionsBeen.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolderONE(LayoutInflater.from(context).inflate(R.layout.item_sections,null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        MyViewHolderONE myViewHolderONE = (MyViewHolderONE) viewHolder;
        SectionsBeen.DataBean resultsBean = list.get(i);
        myViewHolderONE.section_des.setText(resultsBean.getDescription());

        myViewHolderONE.section_kind.setText(resultsBean.getName());
        Glide.with(context).load(resultsBean.getThumbnail()).into(myViewHolderONE.section_bg);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolderONE extends XRecyclerView.ViewHolder{
        private TextView section_des;
        private ImageView section_bg;
        private TextView section_kind;


        public MyViewHolderONE(@NonNull View itemView) {
            super(itemView);


            section_bg = itemView.findViewById(R.id.section_bg);
            section_des = itemView.findViewById(R.id.section_des);
            section_kind = itemView.findViewById(R.id.section_kind);
        }
    }
}
