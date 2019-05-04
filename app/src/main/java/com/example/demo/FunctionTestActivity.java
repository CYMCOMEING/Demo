package com.example.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.demo.R;
import com.example.demo.bean.ItemFun;
import com.example.libandroid.notification.BaseNotification;
import com.example.libandroid.recyclerView.BaseRecycleViewAdapter;
import com.example.libandroid.recyclerView.BaseViewHolder;
import com.example.libandroid.recyclerView.itemDecoration.SimpleDividerDecoration;

import java.util.ArrayList;
import java.util.List;

public class FunctionTestActivity extends AppCompatActivity {

    private RecyclerView mRv_fun;

    private List<ItemFun> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function_test);

        mRv_fun = findViewById(R.id.rv_fun);

        initData();

        BaseRecycleViewAdapter adapter = new BaseRecycleViewAdapter<ItemFun>(this, mList, R.layout.item_layout) {

            @Override
            protected void bindData(BaseViewHolder holder, final ItemFun data, int position) {
                TextView tv_title = holder.getTextView(R.id.tv_title);
                tv_title.setText(data.getTitle());
                tv_title.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        itemonClisk(data.getId());
                    }
                });
            }
        };

        mRv_fun.setLayoutManager(new LinearLayoutManager(this));
        mRv_fun.addItemDecoration(new SimpleDividerDecoration(this));
        mRv_fun.setAdapter(adapter);
    }

    private void initData() {
        mList.add(new ItemFun(1, "通知栏消息"));
        mList.add(new ItemFun(2, "带进度条通知栏"));
        mList.add(new ItemFun(3, "自定义通知栏"));
    }

    private void itemonClisk(int id){
        switch (id){
            case 1:
                new BaseNotification().sendNormalNotification(this);
                break;
            case 2:
                new BaseNotification().sendProgressNotification(this);
                break;
            case 3:
                new BaseNotification().sendCustomNotification(this, MainActivity.class);
                break;
        }
    }
}
