package com.example.wechatapp.utils;

import android.util.Log;

import com.example.wechatapp.BuildConfig;

public class L {

    private static final String TAG = "CYMDEBUG";

    private static boolean sDebug = BuildConfig.DEBUG;

    public static void d(String msg, Object... args){
        if (!sDebug){
            return;
        }
        Log.d(TAG, String.format(msg, args));
    }
}
