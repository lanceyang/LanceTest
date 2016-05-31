package com.lance.lancetest.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by lance on 16/5/31.
 */
public class SameWHFrameLayout extends FrameLayout{

    public SameWHFrameLayout(Context context) {
        super(context,null);
    }

    public SameWHFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SameWHFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(heightMeasureSpec, heightMeasureSpec);
    }
}
