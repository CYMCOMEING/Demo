package com.example.libandroid;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.libandroid.utils.L;

public class BluetoothActivity extends AppCompatActivity {

    private static final int REQUEST_OPEN_BT = 0x01;
    private Button mBtnOpenBt;
    private BluetoothAdapter mBluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        // 获取本地蓝牙的适配器
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        // 判断蓝牙功能是否存在
        if (mBluetoothAdapter == null){
            L.showToash(this,"该设备不支持蓝牙");
//            finish();
            return;
        }
        // 获取名字 MAC地址
        String name = mBluetoothAdapter.getName();
        String mac = mBluetoothAdapter.getAddress();
        L.showToash(this, "名字：" + name + " MAC地址: " + mac);

        // 获取当前蓝牙的状态
        int state = mBluetoothAdapter.getState();
        switch (state){
            case BluetoothAdapter.STATE_ON: break; // 已经打开
            case BluetoothAdapter.STATE_TURNING_ON: break; // 正在打开
            case BluetoothAdapter.STATE_OFF: break; // 已经关闭
            case BluetoothAdapter.STATE_TURNING_OFF: break; // 正在关闭
        }

        mBtnOpenBt = findViewById(R.id.btn_open_bt);
        mBtnOpenBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 关闭--打开本地蓝牙设备
                // 判断蓝牙是否已经打开
                if (mBluetoothAdapter.isEnabled()){
                    L.showToash(BluetoothActivity.this, "蓝牙已经打开");
                    // 关闭蓝牙
                    boolean isClose = mBluetoothAdapter.disable();
                    L.showToash(BluetoothActivity.this, "蓝牙是否关闭:" + isClose);
                } else {
                    // 打开蓝牙
//                    boolean isOpen = mBluetoothAdapter.enable();
//                    L.showToash(BluetoothActivity.this, "蓝牙状态：" + isOpen);

                    // 调用系统API打开
                    Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(intent, REQUEST_OPEN_BT);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUEST_OPEN_BT == requestCode){
            if (resultCode == RESULT_CANCELED){
                L.showToash(BluetoothActivity.this, "请求失败");
            } else {
                L.showToash(BluetoothActivity.this, "请求成功");
            }
        }
    }
}
