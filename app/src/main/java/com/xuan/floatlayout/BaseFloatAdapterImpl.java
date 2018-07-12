package com.xuan.floatlayout;

import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * com.xuan.floatlayout
 *
 * @author by xuan on 2018/7/12
 * @version [版本号, 2018/7/12]
 * @update by xuan on 2018/7/12
 * @descript
 */
public abstract class BaseFloatAdapterImpl extends BaseFloatAdapter {
    public ArrayList<Object> mItemList = new ArrayList<>();
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    public BaseFloatAdapterImpl() {
    }

    public int getLayoutResId(){return 0;}
    public View getLayoutView(){return null;}

    public <T> void addItem(List<T> itemList) {
        mItemList.addAll(itemList);
        onChanged();
    }

    public <T> void addItem(T t) {
        mItemList.add(t);
        onChanged();
    }

    public void remove(int position) {
        mItemList.remove(position);
        onChanged();
    }

    public void clear() {
        mItemList.clear();
        onChanged();
    }

    @Override
    public int getCount() {
        if (mItemList == null || mItemList.size() == 0) {
            return 0;
        } else {
            return mItemList.size();
        }
    }


    @Override
    public View getView(int position, ViewGroup parent) {
        if(getLayoutResId()!=0){
            View view=View.inflate(parent.getContext(),getLayoutResId(),null);
            bindView(position,view);
            return view;
        }else if(getLayoutView()!=null){
            bindView(position,getLayoutView());
            return getLayoutView();
        }else{
            throw new RuntimeException("please Override getLayoutResId or getLayoutView");
        }
    }

    protected abstract void bindView(int position, View layoutView);

    public void addOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }

    public void addOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener){
        this.onItemLongClickListener=onItemLongClickListener;
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
    public interface OnItemLongClickListener {
        void onItemLongClick(View view, int position);
    }
}
