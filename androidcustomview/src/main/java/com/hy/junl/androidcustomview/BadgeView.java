/*
 * Copyright (C) 2015 The Android  Source Project
 *
 * Licensed under the Apache License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.ido517.com
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hy.junl.androidcustomview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 *
 * @Description 带标记的View
 * @author ChansEbm
 * @date 2015年7月3日 下午8:25:50
 */
public class BadgeView extends View {

    private String badgeText;
    private Bitmap badgeBitmap;
    private Paint textPaint;
    private Paint badgePaint;
    private Rect textRect;
    private int badgeTextSize;
    private int badgeTextColor;
    private int badgeColor;
    private int badgePosition;
    private float radius;
    private float badgeRadio = 0.3f;
    private boolean isHasBadge = false;

    public enum BadgeType {
        CENTER(0), LEFTTOP(1), LEFTVERTICAL(2), LEFTBOTTOM(3), RIGHTTOP(4), RIGHTVERTICAL(
                5), RIGHTBOTTOM(6), TOPHORIZATAL(7), BOTTOMHORIZATAL(8);

        private final int value;

        BadgeType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * @param context
     * @param attrs
     */
    public BadgeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.BadgeView);
        int count = a.getIndexCount();
        for (int i = 0; i < count; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.BadgeView_badgeBitmap:
                    badgeBitmap = BitmapFactory.decodeResource(getResources(),
                            a.getResourceId(attr, R.mipmap.ic_launcher));
                    break;
                case R.styleable.BadgeView_badgeColor:
                    badgeColor = a.getColor(attr, Color.RED);
                    break;
                case R.styleable.BadgeView_badgePosition:
                    badgePosition = a.getInt(attr, 0);
                    break;
                case R.styleable.BadgeView_badgeText:
                    badgeText = a.getString(attr);
                    break;
                case R.styleable.BadgeView_badgeTextColor:
                    badgeTextColor = a.getColor(attr, Color.WHITE);
                    break;
                case R.styleable.BadgeView_badgeTextSize:
                    badgeTextSize = a.getDimensionPixelSize(attr, 0);
                    break;
                case R.styleable.BadgeView_badgeRadio:
                    badgeRadio = a.getFloat(attr, 0.3f);
                    break;
                case R.styleable.BadgeView_badgeIsVisibity:
                    isHasBadge = a.getBoolean(attr, false);
                    break;
            }
        }
        a.recycle();
        if (badgeText != null) {
            initTextPaint();
        }
        badgePaint = new Paint();
        badgePaint.setColor(badgeColor);
        badgePaint.setAntiAlias(true);
    }

    private void initTextPaint() {
        textPaint = new Paint();
        textRect = new Rect();
        textPaint.setColor(badgeTextColor);
        textPaint.setTextSize(badgeTextSize);
        textPaint.getTextBounds(badgeText, 0, badgeText.length(), textRect);
        textPaint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);
        int width, height;
        if (specMode == MeasureSpec.EXACTLY) {
            width = specSize;
        } else {
            width = badgeBitmap.getWidth();
        }
        specMode = MeasureSpec.getMode(heightMeasureSpec);
        specSize = MeasureSpec.getSize(heightMeasureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            height = specSize;
        } else {
            height = badgeBitmap.getHeight();
        }
        setMeasuredDimension(width, height);
        calculateScaleBitmap();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        drawBadgeBitmap(canvas);
        if (isHasBadge)
            drawBadgeText(canvas);
    }

    private void drawBadgeText(Canvas canvas) {
        // TODO Auto-generated method stub
        radius = (float) calculateRadius();
        float cx = 0;
        float cy = 0;
        switch (badgePosition) {
            case 0:
                cx = getWidth() / 2;
                cy = getHeight() / 2;
                break;
            case 1:
                cx = getPaddingLeft() + radius;
                cy = getPaddingTop() + radius;
                break;
            case 2:
                cx = getPaddingLeft() + radius;
                cy = getHeight() / 2;
                break;
            case 3:
                cx = getPaddingLeft() + radius;
                cy = getHeight() - getPaddingBottom() - radius;
                break;
            case 4:
                cx = getWidth() - getPaddingRight() - radius;
                cy = getPaddingTop() + radius;
                break;
            case 5:
                cx = getWidth() - getPaddingRight() - radius;
                cy = getHeight() / 2;
                break;
            case 6:
                cx = getWidth() - getPaddingRight() - radius;
                cy = getHeight() - getPaddingBottom() - radius;
                break;
            case 7:
                cx = getWidth() / 2;
                cy = getPaddingTop() + radius;
                break;
            case 8:
                cx = getWidth() / 2;
                cy = getHeight() - getPaddingBottom() - radius;
                break;
        }
        canvas.drawCircle(cx, cy, radius, badgePaint);
        canvas.drawText(badgeText, (float) (cx - textRect.width() / 2.0f),
                (float) (cy + textRect.height() / 2.0f), textPaint);
    }

    private void drawBadgeBitmap(Canvas canvas) {
        // TODO Auto-generated method stub
        int left = getWidth() / 2 - badgeBitmap.getWidth()/2
                + getPaddingLeft() - getPaddingRight();
        int top = getHeight() / 2 - badgeBitmap.getHeight()/2
                + getPaddingTop() - getPaddingBottom();
        canvas.drawBitmap(badgeBitmap, left, top, null);
    }

    private int calculateRadius() {
        // TODO Auto-generated method stub
        int totalArea = badgeBitmap.getWidth() * badgeBitmap.getHeight();
        return (int) Math.sqrt(totalArea * badgeRadio / Math.PI);
    }

    private void calculateScaleBitmap() {
        // TODO Auto-generated method stub
        int min = Math.min(getMeasuredHeight(), getMeasuredHeight());
        if (badgeBitmap.getWidth() > getMeasuredWidth()
                || badgeBitmap.getHeight() > getMeasuredHeight()) {
            badgeBitmap = Bitmap
                    .createScaledBitmap(badgeBitmap, min, min, true);
        }
    }

    public void setBadgeGravity(BadgeType badgeType) {
        this.badgePosition = badgeType.getValue();
        postInvalidate();
    }

    public void setBadgeBitmapResource(int resId) {
        this.badgeBitmap = BitmapFactory.decodeResource(getResources(), resId);
        postInvalidate();
    }

    public void setBadgeText(String text) {
        this.badgeText = text;
        postInvalidate();
    }

    public void setBadgeTextSize(int sp) {
        float scale = getResources().getDisplayMetrics().scaledDensity;
        this.badgeTextSize = (int) (sp * scale + 0.5f);
        textPaint.setTextSize(badgeTextSize);
        postInvalidate();
    }

    public void setBadgeTextColor(int color) {
        this.badgeColor = color;
        textPaint.setColor(color);
        postInvalidate();
    }

    public void setBadgeRadio(float radio) {
        this.badgeRadio = radio;
        postInvalidate();
    }

    public void setBadgeIsVisibility(boolean visibity) {
        isHasBadge = visibity;
        postInvalidate();
    }
}
