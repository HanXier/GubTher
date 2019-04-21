package com.example.lenovo.newgeeknew.Utils;


/**
 * @author: 汐er
 * @QQ: 2044273990
 * @date: 2019/4/19
 * @Week: 星期五
 * @GitHub: https://github.com/HanXier
 */
public interface TouchCallBack {
    //交换条目位置
    void onItemMove(int fromPosition,int toPosition);
    //删除条目
    void onItemDelete(int position);
}
