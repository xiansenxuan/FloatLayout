package com.xuan.floatlayout;

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
    private final DataSetObservable mDataSetObservable = new DataSetObservable();

    //有多少条目
    public abstract int getCount();

    //获取当前view
    public abstract View getView(int position, ViewGroup parent);

    public void registerDataSetObserver(FloatLayoutObserver observer) {
        mDataSetObservable.registerObserver(observer);
    }

    public void unregisterDataSetObserver(FloatLayoutObserver observer) {
        mDataSetObservable.unregisterObserver(observer);
    }

    public void isVisibleAddView(){
        mDataSetObservable.notifyIsVisibleAddView();
    }

    public void onChanged(){
        mDataSetObservable.notifyChanged();
    }
}
