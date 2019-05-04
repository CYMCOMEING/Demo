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

import com.example.wechatapp.fragment.TabFragment;
import com.example.wechatapp.utils.L;
import com.example.wechatapp.view.TabView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivityWithTab extends AppCompatActivity {

    private ViewPager mVpMian;
    private List<String> mTitles = new ArrayList<>(Arrays.asList("微信", "通讯录", "发现", "我"));

    private TabView mBtnWeChat;
    private TabView mBtnFriend;
    private TabView mBtnFind;
    private TabView mBtnMine;

    private List<TabView> mTabs = new ArrayList<>();

    private SparseArray<TabFragment> mFragments = new SparseArray<>();

    private static final String BUNDLE_KEY_POS = "bundle_key_pos";
    private int mCurTabPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tab);

        if (savedInstanceState != null){
            mCurTabPos = savedInstanceState.getInt(BUNDLE_KEY_POS, 0);
            L.d("mCurTabPos " + mCurTabPos);
        }

        initViews();

        initViewPageAdapter();

        initEvents();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        L.d("onSaveInstanceState " + mVpMian.getCurrentItem());
        outState.putInt(BUNDLE_KEY_POS, mVpMian.getCurrentItem());
        super.onSaveInstanceState(outState);
    }

    private void initEvents() {
        for (int i = 0; i < mTabs.size(); i++) {
            TabView tabView = mTabs.get(i);
            final int finalI = i;
            tabView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mVpMian.setCurrentItem(finalI, false);
                    setCurrentTab(finalI);
                }
            });
        }
    }

    private void initViewPageAdapter() {
        mVpMian.setOffscreenPageLimit(mTitles.size());
        mVpMian.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                TabFragment fragment = TabFragment.newInstance(mTitles.get(i));
                return fragment;
            }

            @Override
            public int getCount() {
                return mTitles.size();
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                TabFragment tabFragment = (TabFragment) super.instantiateItem(container, position);
                mFragments.put(position, tabFragment);
                return tabFragment;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                mFragments.remove(position);
                super.destroyItem(container, position, object);
            }
        });

        mVpMian.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // 左->右 0->1 , left pos, right pos + 1, positionOffset 0~1
                // left progress: 1~0(1-positionOffset); right progress: 0~1 positionOffset

                // 右->左 1->0 , left pos, right pos + 1, positionOffset 1~0
                // left progress: 0~1(1-positionOffset); right progress: 0~1 positionOffset

                if (positionOffset > 0) {
                    TabView left = mTabs.get(position);
                    TabView right = mTabs.get(position + 1);

                    left.setProgress((1 - positionOffset));
                    right.setProgress(positionOffset);
                }

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initViews() {
        mVpMian = findViewById(R.id.vp_main);
        mBtnWeChat = findViewById(R.id.tab_wechat);
        mBtnFriend = findViewById(R.id.tab_friend);
        mBtnFind = findViewById(R.id.tab_find);
        mBtnMine = findViewById(R.id.tab_mine);

        mBtnWeChat.setIconAndText(R.drawable.wechat, R.drawable.wechat_select, "微信");
        mBtnFriend.setIconAndText(R.drawable.friend, R.drawable.friend_select, "通讯录");
        mBtnFind.setIconAndText(R.drawable.find, R.drawable.find_select, "发现");
        mBtnMine.setIconAndText(R.drawable.mine, R.drawable.mine_select, "我");

        mTabs.add(mBtnWeChat);
        mTabs.add(mBtnFriend);
        mTabs.add(mBtnFind);
        mTabs.add(mBtnMine);

        setCurrentTab(mCurTabPos);
    }

    private void setCurrentTab(int pos) {
        for (int i = 0; i < mTabs.size(); i++) {
            TabView tabView = mTabs.get(i);
            if (i == pos) {
                tabView.setProgress(1);
            } else {
                tabView.setProgress(0);
            }
        }
    }
}
