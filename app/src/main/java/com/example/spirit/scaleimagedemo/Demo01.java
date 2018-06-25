package com.example.spirit.scaleimagedemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 图片移动demo
 */
public class Demo01 extends android.support.v7.widget.AppCompatImageView implements View
        .OnTouchListener {
    private PointF startPoint = new PointF();
    private Matrix mCurrentMatrix = new Matrix();

    public Demo01(Context context) {
        this(context, null);
    }

    public Demo01(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Demo01(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //startPoint.set(event.getRawX(), event.getRawY());
                startPoint.set(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                //float dx = event.getRawX() - startPoint.x;
                //float dy = event.getRawY() - startPoint.y;
                mCurrentMatrix.set(getImageMatrix());
                float dx = event.getX() - startPoint.x;
                float dy = event.getY() - startPoint.y;
                mCurrentMatrix.postTranslate(dx, dy);
                setImageMatrix(mCurrentMatrix);
                System.out.println();
                //startPoint.set(event.getRawX(), event.getRawY());
                startPoint.set(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
    }
}
