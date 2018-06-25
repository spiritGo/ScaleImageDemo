package com.example.spirit.scaleimagedemo;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 图片移动缩放综合demo
 */
public class Demo03 extends android.support.v7.widget.AppCompatImageView implements View
        .OnTouchListener {

    private PointF startPoint = new PointF();
    private float startDis;
    private float endDis;
    final private int MODE_DRAG = 0;
    final private int MODE_ZOOM = 1;
    private int currentMode = MODE_DRAG;
    private Matrix currentMatrix = new Matrix();

    public Demo03(Context context) {
        this(context, null);
    }

    public Demo03(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Demo03(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                currentMode = MODE_DRAG;
                startPoint.set(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                currentMode = MODE_ZOOM;
                startDis = distance(event);
                break;
            case MotionEvent.ACTION_MOVE:
                if (currentMode == MODE_DRAG) {
                    currentMatrix.set(getImageMatrix());
                    float dx = event.getX() - startPoint.x;
                    float dy = event.getY() - startPoint.y;
                    System.out.println(dx + ", " + dy);
                    currentMatrix.postTranslate(dx, dy);
                    setImageMatrix(currentMatrix);
                    startPoint.set(event.getX(), event.getY());
                } else if (currentMode == MODE_ZOOM) {
                    if (event.getPointerCount() >= 2) {
                        endDis = distance(event);
                        if (endDis > 10f) {
                            float scale = endDis / startDis;
                            startDis = endDis;
                            currentMatrix.set(getImageMatrix());
                            currentMatrix.postScale(scale, scale, getWidth() / 2, getHeight() / 2);
                            setImageMatrix(currentMatrix);
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }

    private float distance(MotionEvent event) {
        float dx = event.getX(1) - event.getX(0);
        float dy = event.getY(1) - event.getY(0);
        return (float) Math.sqrt(dx * dx + dy * dy);
    }
}
