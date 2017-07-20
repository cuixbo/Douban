package com.xbc.douban.movie.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

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

        public static final int HORIZONTAL = LinearLayout.HORIZONTAL;
        public static final int VERTICAL = LinearLayout.VERTICAL;

        private static final int[] ATTRS = new int[]{android.R.attr.listDivider};

        private Drawable mDivider;

        private int marginLeft, marginRight, marginTop, marginBottom;

        private int mOrientation;

        private final Rect mBounds = new Rect();

        public InsetDividerItemDecoration(Context context, int orientation) {
            final TypedArray a = context.obtainStyledAttributes(ATTRS);
            mDivider = a.getDrawable(0);
            a.recycle();
            setOrientation(orientation);
        }

        public InsetDividerItemDecoration setMargin(int l, int r, int t, int b) {
            marginLeft = l;
            marginRight = r;
            marginTop = t;
            marginBottom = b;
            return this;
        }

        public void setOrientation(int orientation) {
            if (orientation != HORIZONTAL && orientation != VERTICAL) {
                throw new IllegalArgumentException(
                        "Invalid orientation. It should be either HORIZONTAL or VERTICAL");
            }
            mOrientation = orientation;
        }

        public void setDrawable(@NonNull Drawable drawable) {
            if (drawable == null) {
                throw new IllegalArgumentException("Drawable cannot be null.");
            }
            mDivider = drawable;
        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            if (parent.getLayoutManager() == null) {
                return;
            }
            if (mOrientation == VERTICAL) {
                drawVertical(c, parent);
            } else {
                drawHorizontal(c, parent);
            }
        }

        @SuppressLint("NewApi")
        private void drawVertical(Canvas canvas, RecyclerView parent) {
            canvas.save();
            final int left;
            final int right;
            if (parent.getClipToPadding()) {
                left = parent.getPaddingLeft() + marginLeft;
                right = parent.getWidth() - parent.getPaddingRight() - marginRight;
                canvas.clipRect(left, parent.getPaddingTop(), right,
                                parent.getHeight() - parent.getPaddingBottom());
            } else {
                left = 0 + marginLeft;
                right = parent.getWidth() - marginRight;
            }

            final int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                final View child = parent.getChildAt(i);
                parent.getDecoratedBoundsWithMargins(child, mBounds);
                final int bottom = mBounds.bottom + Math.round(ViewCompat.getTranslationY(child));
                final int top = bottom - mDivider.getIntrinsicHeight();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(canvas);
            }
            canvas.restore();
        }

        @SuppressLint("NewApi")
        private void drawHorizontal(Canvas canvas, RecyclerView parent) {
            canvas.save();
            final int top;
            final int bottom;
            if (parent.getClipToPadding()) {
                top = parent.getPaddingTop() + marginTop;
                bottom = parent.getHeight() - parent.getPaddingBottom() - marginBottom;
                canvas.clipRect(parent.getPaddingLeft(), top,
                                parent.getWidth() - parent.getPaddingRight(), bottom);
            } else {
                top = 0 + marginTop;
                bottom = parent.getHeight() - marginBottom;
            }

            final int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                final View child = parent.getChildAt(i);
                parent.getLayoutManager().getDecoratedBoundsWithMargins(child, mBounds);
                final int right = mBounds.right + Math.round(ViewCompat.getTranslationX(child));
                final int left = right - mDivider.getIntrinsicWidth();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(canvas);
            }
            canvas.restore();
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                                   RecyclerView.State state) {
            if (mOrientation == VERTICAL) {
                outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
            } else {
                outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
            }
        }

    }



}
