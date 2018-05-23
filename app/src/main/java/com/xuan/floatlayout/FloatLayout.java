package com.xuan.floatlayout;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * com.xuan.floatlayout
 *
 * @author by xuan on 2018/5/23
 * @version [版本号, 2018/5/23]
 * @update by xuan on 2018/5/23
 * @descript
 */
public class FloatLayout extends ViewGroup {

    ArrayList<ArrayList<View>> itemList;

    private BaseFloatAdapter adapter;

    public FloatLayout(Context context) {
        this(context,null);
    }

    public FloatLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FloatLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



//    浮动不规则宽高自定义布局 :
//    1.1 onMeasure() 指定宽高
//    1.2 for循环测量子view
//    1.3 根据子view计算和指定自己的布局
//
//    2.1 onLayout() 指定位置
//    2.2 for循环摆放指定子view
//
//    3.1 onDraw()


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //总得集合
        itemList=new ArrayList<>();
        ArrayList<View> childList = new ArrayList<>();

        //宽度
        int width=MeasureSpec.getSize(widthMeasureSpec);
        //高度需要计算
        int height=getPaddingTop()+getPaddingBottom();
        //一行的宽度
        int lineWidth=getPaddingLeft()+getPaddingRight();
        //当前行内某个元素最大高度
        int maxHeight=0;

        //循环测量子view宽高
        for (int i = 0; i < getChildCount(); i++) {
            View childView=getChildAt(i);
            //测量子布局宽高 这段代码会调用view的onMeasure()获得宽高
            measureChild(childView,widthMeasureSpec,heightMeasureSpec);

            // 计算layout_margin
            // ViewGroup的layoutParams没有margin属性
            // 需要使用系统的 MarginLayoutParams
            MarginLayoutParams layoutParams = (MarginLayoutParams) childView.getLayoutParams();

            //计算自己的宽高
            //什么时候去换行 一行填满不够放下下一个view的时候
            //当前行宽和下一个view的宽度 大于了 整个宽度 换行
            if(lineWidth+childView.getMeasuredWidth()
                    +layoutParams.rightMargin+layoutParams.rightMargin>width){
                //换行之后宽度重置
                lineWidth=getPaddingLeft()+getPaddingRight()+
                        childView.getMeasuredWidth()+layoutParams.leftMargin+layoutParams.rightMargin;
                //换行之后高度累加 并且清零上一行最大高度 避免下一行的最大高度取到上一行的值
                height+=maxHeight;

                //换行之前添加到大的集合
                itemList.add(childList);
                //每次需要换行的时候 重新初始化集合 并且添加当前需要换行的到下一个集合
                childList=new ArrayList<>();
                childList.add(childView);

            }else{
                //不需要换行 累加子view的宽度
                lineWidth+=childView.getMeasuredWidth()+layoutParams.leftMargin+layoutParams.rightMargin;

                //保存当前行的view到一个list
                childList.add(childView);

                //得出每一行最高的
                maxHeight=Math.max(childView.getMeasuredHeight()
                        +layoutParams.topMargin+layoutParams.bottomMargin,maxHeight);
            }

        }

//        //当前只有一行 itemList并没有进入if()换行添加过 需要手动添加一次
//        //并且 height也没有进入if()需要 手动计算一次
//        if(itemList.size()==0 && childList.size()>0){
//            itemList.add(childList);
//            height+=maxHeight;
//        }

        //当前是走后一行，没有进入if() 没有走 height+=maxHeight; itemList.add(childList);
        //必须手动设置一次 否则会缺少最后一行的数据
        height+=maxHeight;
        itemList.add(childList);

        setMeasuredDimension(width,height);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //循环摆放子view
        int left,top,right,bottom;

        //需要累加的行高 用于计算top位置 (默认加上top的padding)
        int lineHeight=getPaddingTop();

        for (ArrayList<View> views : itemList) {
            //left每次换行重新初始化
            left=getPaddingLeft();

            //每一行的某个元素最大高度
            int maxHeight=0;

            //每一个行的view
            for (View view : views) {
                MarginLayoutParams layoutParams = (MarginLayoutParams) view.getLayoutParams();

                left+=layoutParams.leftMargin;
                top=layoutParams.topMargin+lineHeight;

                right=left+view.getMeasuredWidth();
                bottom=top+view.getMeasuredHeight();

                Log.i("TAG"," l " +left + " t " +top+" r " +right +" b " +bottom);

                view.layout(left,top,right,bottom);

                maxHeight=Math.max(view.getMeasuredHeight()
                        +layoutParams.topMargin+layoutParams.bottomMargin,maxHeight);

                left+=view.getMeasuredWidth()+layoutParams.rightMargin;
            }
            //每一行循环结束 累加上一行的高度
            lineHeight+=maxHeight;
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        //采用 LinearLayout 的 generateLayoutParams 方法 去强转
        return new MarginLayoutParams(getContext(),attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override//没有设置MarginLayoutParams 会导致强转失败 需要手动覆盖设置默认的
                //MarginLayoutParams
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(MarginLayoutParams.WRAP_CONTENT,MarginLayoutParams.WRAP_CONTENT);
    }

    //    这种方式比较耦合  复用性不强 最好使用adapter方式
//    public void addTag(List<String> tags){
//        for (String tag : tags) {
//
//        }
//    }

    public void setAdapter(BaseFloatAdapter adapter){
        if(adapter==null){
            throw new RuntimeException("请设置BaseFloatAdapter");
        }
        this.adapter=adapter;

        removeAllViews();

        int childCount=adapter.getCount();
        for (int i = 0; i < childCount; i++) {
            View childView=adapter.getView(i,this);
            addView(childView);
        }
    }

}
