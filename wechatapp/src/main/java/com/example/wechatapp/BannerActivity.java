package com.example.wechatapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.example.wechatapp.fragment.SplashFragment;
import com.example.wechatapp.view.transformer.RotateTransform;
import com.example.wechatapp.view.transformer.ScaleTransformer;

public class BannerActivity extends AppCompatActivity {

    private ViewPager mVpMian;

    private int[] mResIds = new int[]{
            0xffff0000,
            0xff00ff00,
            0xff0000ff,
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);

        mVpMian = findViewById(R.id.vp_main);
        mVpMian.setOffscreenPageLimit(3); //
        mVpMian.setPageMargin(40);  // use dp
        mVpMian.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return mResIds.length;
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
                return view == o;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                View view = new View(container.getContext());
                view.setBackgroundColor(mResIds[position]);
                ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView((View) object);
            }
        });

        mVpMian.setPageTransformer(true, new RotateTransform());
    }


}
