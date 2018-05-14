package com.xbc.douban.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.xbc.douban.R;
import com.xbc.douban.util.Log;

public class IconTextView extends RelativeLayout {

    private IconView mIconLeft;

    public IconTextView(Context context) {
        this(context, null);
    }

    public IconTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IconTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.IconTextView, defStyleAttr, 0);
        if (a != null) {
            String left = a.getString(R.styleable.IconTextView_drawableLeft);
            Log.log(left);
            mIconLeft = new IconView(context);
            mIconLeft.setText(left);
            addView(mIconLeft);
        }
    }

}
