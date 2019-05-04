package com.example.wechatapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import com.example.wechatapp.fragment.SplashFragment;
import com.example.wechatapp.fragment.TabFragment;
import com.example.wechatapp.utils.L;
import com.example.wechatapp.view.TabView;
import com.example.wechatapp.view.transformer.ScaleTransformer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SplashActivity extends AppCompatActivity {

    private ViewPager mVpMian;

    private int[] mResIds = new int[]{
            R.drawable.guide1,
            R.drawable.guide2,
            R.drawable.guide3,
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mVpMian = findViewById(R.id.vp_main);
        mVpMian.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return SplashFragment.newInstarnce(mResIds[i]);
            }

            @Override
            public int getCount() {
                return mResIds.length;
            }
        });

        mVpMian.setPageTransformer(true, new ScaleTransformer());
    }


}
