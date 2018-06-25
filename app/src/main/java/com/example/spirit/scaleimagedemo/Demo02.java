package com.example.spirit.scaleimagedemo;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 图片缩放demo
 */
public class Demo02 extends android.support.v7.widget.AppCompatImageView implements View
        .OnTouchListener {

    private PointF startPoint = new PointF();
    private Matrix currentMatrix = new Matrix();
    private Matrix matrix = new Matrix();
    private Double startDis;
    private Double endDis;

    public Demo02(Context context) {
        this(context, null);
    }

    public Demo02(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Demo02(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                startDis = distance(event);
                break;
            case MotionEvent.ACTION_MOVE:
                if (event.getPointerCount() < 2) {
                    return false;
                }

                endDis = distance(event);
                if (endDis > 10) {
                    float scale = (float) (endDis / startDis);
                    startDis = endDis;
                    currentMatrix.set(getImageMatrix());

                    currentMatrix.postScale(scale, scale, getWidth() / 2, getHeight() / 2);
                    setImageMatrix(currentMatrix);
                }

                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }

    private double distance(MotionEvent event) {
        float dx = event.getX(1) - event.getX(0);
        float dy = event.getY(1) - event.getY(0);
        return Math.sqrt(dx * dx + dy * dy);
    }
}
