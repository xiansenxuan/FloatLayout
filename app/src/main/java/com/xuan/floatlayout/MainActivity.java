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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatLayout float_layout=findViewById(R.id.float_layout);

        itemList=new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            if(i%2==0){
                itemList.add("我很短 "+i);
            }else if(i%3==0){
                itemList.add("我的名字为什么那么长 "+i);
            }else if(i%5==0){
                itemList.add("我那么长 "+i);
            }else{
                itemList.add("我的名字短 "+i);
            }

        }

        MyFloatAdapter adapter=new MyFloatAdapter();
        float_layout.setAdapter(adapter);

    }

    private class MyFloatAdapter extends BaseFloatAdapter{

        @Override
        public int getCount() {
            return itemList.size();
        }

        @Override
        public View getView(int position, ViewGroup parent) {
            TextView view=new TextView(MainActivity.this);
            view.setPadding(30,30,30,30);
            view.setBackgroundResource(R.drawable.item_tag_bg);
            view.setTextSize(16);
            view.setText(itemList.get(position));

            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(10,10,10,10);

            view.setLayoutParams(params);

            return view;
        }
    }
}
