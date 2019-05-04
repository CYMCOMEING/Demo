package com.example.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.demo.bean.Item;
import com.example.libandroid.BluetoothActivity;
import com.example.libandroid.recyclerView.BaseRecycleViewAdapter;
import com.example.libandroid.recyclerView.BaseViewHolder;
import com.example.libandroid.recyclerView.itemDecoration.SimpleDividerDecoration;
import com.example.wechatapp.MainActivityWithTab;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRv_main;

    private List<Item> mList = new ArrayList<>();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();

        mRv_main = findViewById(R.id.rv_main);

        BaseRecycleViewAdapter adapter = new BaseRecycleViewAdapter<Item>(this, mList, R.layout.item_layout) {
            @Override
            protected void bindData(BaseViewHolder holder, Item data, final int position) {
                TextView tv_title = holder.getTextView(R.id.tv_title);
                tv_title.setText(data.getmTitle());
                tv_title.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        itemonClisk(position);
                    }
                });
            }
        };

        mRv_main.setLayoutManager(new LinearLayoutManager(this));
        mRv_main.addItemDecoration(new SimpleDividerDecoration(this));
        mRv_main.setAdapter(adapter);
    }

    public void itemonClisk(int position){
        Intent intent = new Intent(MainActivity.this, mList.get(position).getmClass());
        startActivity(intent);
    }

    private void initData() {
        mList.add(new Item("仿微信界面", MainActivityWithTab.class));
        mList.add(new Item("高德地图", com.example.libandroid.amap.AMapActivity.class));
        mList.add(new Item("蓝牙", BluetoothActivity.class));
        mList.add(new Item("测试功能", FunctionTestActivity.class));
    }
}
