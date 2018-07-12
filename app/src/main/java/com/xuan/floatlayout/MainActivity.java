package com.xuan.floatlayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> itemList;
    private MyFloatAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatLayout float_layout = findViewById(R.id.float_layout);

        itemList = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            if (i % 2 == 0) {
                itemList.add("我很短 " + i);
            } else if (i % 3 == 0) {
                itemList.add("我的名字那么长 " + i);
            } else if (i % 5 == 0) {
                itemList.add("我长 " + i);
            } else {
                itemList.add("短 " + i);
            }

        }

        adapter = new MyFloatAdapter();
        float_layout.setAdapter(adapter);
        adapter.addItem(itemList);

    }

    public void addItem(View view) {
        adapter.addItem("新增的");
        adapter.addItem("新增的");
    }

    public void reduceItem(View view) {
        adapter.remove(4);
    }

    private class MyFloatAdapter extends BaseFloatAdapterImpl {
//        @Override
//        public View getLayoutView() {
//            TextView view=new TextView(MainActivity.this);
//            return view;
//        }
//
//        @Override
//        protected void bindView(int position, View layoutView) {
//            TextView view= (TextView) layoutView;
//
//            view.setPadding(16,16,16,16);
//            view.setBackgroundResource(R.drawable.item_tag_bg);
//            view.setTextSize(14);
//            view.setText((String)adapter.mItemList.get(position));
//
//            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(
//                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
//            );
//            params.setMargins(10,10,10,10);
//
//            view.setLayoutParams(params);
//        }

        @Override
        public View getView(int position, ViewGroup parent) {
            TextView view = new TextView(MainActivity.this);
            view.setPadding(16, 16, 16, 16);
            view.setBackgroundResource(R.drawable.item_tag_bg);
            view.setTextSize(14);
            view.setText((CharSequence) adapter.mItemList.get(position));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(10, 10, 10, 10);

            view.setLayoutParams(params);

            return view;
        }

        @Override
        protected void bindView(int position, View layoutView) {

        }
    }

//    private class MyFloatAdapter extends BaseFloatAdapter{
//
//        @Override
//        public int getCount() {
//            return itemList.size();
//        }
//
//        @Override
//        public View getView(int position, ViewGroup parent) {
//            TextView view=new TextView(MainActivity.this);
//            view.setPadding(16,16,16,16);
//            view.setBackgroundResource(R.drawable.item_tag_bg);
//            view.setTextSize(14);
//            view.setText(itemList.get(position));
//
//            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(
//                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
//            );
//            params.setMargins(10,10,10,10);
//
//            view.setLayoutParams(params);
//
//            return view;
//        }
//    }
}
