package com.example.wechatapp.view.transformer;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.wechatapp.utils.L;

public class ScaleTransformer implements ViewPager.PageTransformer {

    private static final float MIN_SCALE = 0.75f;
    private static final float MIN_ALPHA = 0.5f;

    @Override
    public void transformPage(@NonNull View view, float postion) {
        // L.d("cym: " + view.getTag()+ " , pos = " + postion);

        // a->b
        // a , postion : (0,-1)
        // b , postion : (1,0)

        // b->a
        // a , position : (-1,0)
        // b , position : (0,1)

        // [,-1]
        if (postion < -1){
            view.setScaleX(MIN_SCALE);
            view.setScaleY(MIN_SCALE);
            view.setAlpha(MIN_ALPHA);
            // [-1,1]
        } else if (postion <= 1){

            // 左边这个页面
            if (postion < 0){
                // a->b postion : (0,-1)
                // [1,0.75f]
                float scaleA = MIN_SCALE +  (1 - MIN_SCALE) * (1 + postion);
                view.setScaleX(scaleA);
                view.setScaleY(scaleA);

                // [1,0.5f]
                float alphaA = MIN_ALPHA + (1 - MIN_ALPHA) * (1+ postion);
                view.setAlpha(alphaA);

                // b->a position : (-1,0)
                // [0.75f,1]

            } else { // 右边的这个页面
                // a->b
                // b , position : (1, 0)
                // [0.75f,1]
                float scaleB = MIN_SCALE +  (1 - MIN_SCALE) * (1 - postion);
                view.setScaleX(scaleB);
                view.setScaleY(scaleB);

                float alphaB = MIN_ALPHA + (1 + MIN_ALPHA) * (1 - postion);
                // b->a
                // b , position : (0， 1)
                // [1,0.75f]

            }
            // [1,]
        } else {
            view.setScaleX(MIN_SCALE);
            view.setScaleY(MIN_SCALE);
            view.setAlpha(MIN_ALPHA);
        }
    }
}
