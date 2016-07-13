package com.example.movie.View;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by 潇舰 on 2016/7/10.
 */
public class MyScrollview extends ScrollView {
    public MyScrollview(Context context) {
        super(context);
    }

    public MyScrollview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    protected int computeScrollDeltaToGetChildRectOnScreen(Rect paramRect)
    {
        return 0;
    }
}
