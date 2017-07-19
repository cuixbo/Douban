package com.xbc.douban.movie.adapter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by xiaobocui on 2017/7/19.
 */

public abstract class RecyclerViewHelper {

    public interface OnRecycleViewItemClickListener {
        void onItemClick(View item, int position);
    }

    public interface OnRecycleViewItemLongClickListener {
        void onItemLongClick(View item, int position);
    }

    public static class InsetDividerItemDecoration extends RecyclerView.ItemDecoration {

        public InsetDividerItemDecoration(Context context) {

        }



        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDraw(c, parent, state);
            Paint paint=new Paint();
            paint.setColor(Color.RED);
            paint.setStrokeWidth(4);
            c.drawLine(0.0f,0.0f,100.0f,0.0f,paint);
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.left = 40;
//            view.setPadding(30,0,0,0);
//            outRect.bottom=3;
//            outRect.top=2;
//            outRect.right=5;
        }
    }
}
