package com.example.lenovo.newgeeknew.Adapters;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.lenovo.newgeeknew.Beens.GoldTitleBean;
import com.example.lenovo.newgeeknew.R;
import com.example.lenovo.newgeeknew.Utils.TouchCallBack;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: 汐er
 * @QQ: 2044273990
 * @date: 2019/4/18
 * @Week: 星期四
 * @GitHub: https://github.com/HanXier
 */
public class DailyAdapter extends RecyclerView.Adapter implements TouchCallBack{
    private ArrayList<GoldTitleBean> mTitles;

    public DailyAdapter(ArrayList<GoldTitleBean> titles) {
        mTitles = titles;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_show, null);
        return new VH(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh = (VH) holder;
        final GoldTitleBean bean = mTitles.get(position);
        vh.mTv.setText(bean.title);
        vh.mSc.setChecked(bean.isChecked);
        vh.mSc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                bean.isChecked = isChecked;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTitles.size();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        //交换集合中两个数据的位置
        Collections.swap(mTitles,fromPosition,toPosition);
        //刷新界面,局部刷新,索引会混乱
        notifyItemMoved(fromPosition,toPosition);
    }


    public void onItemDelete(int position) {
        mTitles.remove(position);
        //局部刷新,索引会混乱+集合越界
        notifyItemRemoved(position);
    }
    class VH extends RecyclerView.ViewHolder{
        TextView mTv;
        SwitchCompat mSc;
        public VH(View itemView) {
            super(itemView);

            mTv=itemView.findViewById(R.id.tv);
            mSc=itemView.findViewById(R.id.sc);
        }
    }
}
