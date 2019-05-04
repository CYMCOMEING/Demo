package com.example.wechatapp.view.transformer;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;

public class RotateTransform implements ViewPager.PageTransformer {

    private static final int MAX_ROTATE = 15;

    // a->b
    // a , postion : (0,-1)
    // b , postion : (1,0)

    // b->a
    // a , position : (-1,0)
    // b , position : (0,1)


    @Override
    public void transformPage(@NonNull View view, float postion) {
        // [,-1]
        if (postion < -1) {
            // [-1,1]
            view.setRotation(-MAX_ROTATE);
            view.setPivotY(view.getHeight());
            view.setPivotX(view.getWidth());
        } else if (postion <= 1) {

            // 左边这个页面
            if (postion < 0) {

                view.setPivotY(view.getHeight());
                // 0.5 , w
                view.setPivotX(0.5f * view.getWidth() + 0.5f * (-postion) * view.getWidth());
                // (0,-1) -> 0 , -max
                view.setRotation(MAX_ROTATE * postion);

            } else { // 右边的这个页面
                // a->b
                // b , position : (1, 0)
                view.setPivotY(view.getHeight());
                // 0 , 0.5w
                view.setPivotX(view.getWidth() * 0.5f * (1 - postion));
                // MAX,0
                view.setRotation(MAX_ROTATE * postion);


            }
            // [1,]
        } else {
            view.setRotation(MAX_ROTATE);
            view.setPivotY(view.getHeight());
            view.setPivotX(0);
        }
    }
}
