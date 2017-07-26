package com.xbc.douban.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * iconfont
 * Created by xiaobocui on 2017/7/19.
 */

public class IconView extends android.support.v7.widget.AppCompatTextView {

    private static Typeface iconfontTypeface;

    public IconView(Context context) {
        super(context);
        init();
    }

    public IconView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public IconView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        if (iconfontTypeface==null) {
            iconfontTypeface=Typeface.createFromAsset(getContext().getAssets(),"iconfont.ttf");
        }
        this.setTypeface(iconfontTypeface);
    }



}
