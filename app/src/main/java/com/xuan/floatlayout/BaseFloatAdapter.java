package com.xuan.floatlayout;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;

/**
 * com.xuan.floatlayout
 *
 * @author by xuan on 2018/5/23
 * @version [版本号, 2018/5/23]
 * @update by xuan on 2018/5/23
 * @descript
 */
public abstract class BaseFloatAdapter {

    //有多少条目
    public abstract int getCount();

    //获取当前view
    public abstract View getView(int position, ViewGroup parent);

    //观察者模式 通知更新
    public void unregisterDataSetObserver(DataSetObserver observer){

    }

    public void registerDataSetObserver(DataSetObserver observer){

    }
}
