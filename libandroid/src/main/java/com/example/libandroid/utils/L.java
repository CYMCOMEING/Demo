package com.example.libandroid.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

public class L {
    public static void showToash(Context context, String text){
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public static void showToashCenter(Context context, String text){
        Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }

    public static  void log(String text){
        System.out.println(text);
    }
}
