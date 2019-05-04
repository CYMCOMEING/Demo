package com.example.libandroid.recyclerView.itemDecoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.libandroid.R;

public class SimpleDividerDecoration extends RecyclerView.ItemDecoration {

    private int dividerHeight;
    private Paint dividerPaint;
    private Context mContext;

    public SimpleDividerDecoration(Context context) {
        mContext = context;
        dividerPaint = new Paint();
//        dividerPaint.setColor(context.getResources().getColor(R.color.colorAccent));
//        dividerHeight = context.getResources().getDimensionPixelSize(R.dimen.divider_height);
        dividerPaint.setColor(0xff000000);
        dividerHeight = 1;
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = dividerHeight;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int childCount = parent.getChildCount();
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        for (int i = 0; i < childCount - 1; i++) {
            View view = parent.getChildAt(i);
            float top = view.getBottom();
            float bottom = view.getBottom() + dividerHeight;
            c.drawRect(left, top, right, bottom, dividerPaint);
        }
    }

    public SimpleDividerDecoration setBackgroundColor(int color){
        dividerPaint.setColor(color);
        return this;
    }

    public SimpleDividerDecoration sethight(int hight){
        dividerHeight = 10;
        return this;
    }
}