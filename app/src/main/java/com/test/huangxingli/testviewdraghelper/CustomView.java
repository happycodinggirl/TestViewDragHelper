package com.test.huangxingli.testviewdraghelper;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.text.LoginFilter;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by huangxingli on 2015/3/30.
 */
public class CustomView extends LinearLayout {
    ViewDragHelper dragHelper;
    static final String TAG="MTAG";
    View mDragView1,mDragView2;
    int screenWidth,screenHeight;
    int mTop;
    Context context=getContext();
    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;

        init();
    }



    public CustomView(Context context, AttributeSet attrs, int defStyleAttr, ViewDragHelper dragHelper) {
        super(context, attrs, defStyleAttr);
        this.dragHelper = dragHelper;
        this.context=context;

        init();
    }

    public CustomView(Context context, ViewDragHelper dragHelper) {
        super(context);
        this.dragHelper = dragHelper;
        this.context=context;

        init();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mDragView1=getChildAt(0);
        mDragView2=getChildAt(1);
        Log.v("TAG","====mDragView 1 is "+mDragView1+"---22 IS "+mDragView2);
    }

    public void init(){
        Log.v("TAG","=====INIT ");
        dragHelper=ViewDragHelper.create(this,1.0f,new DragCallback() );


        screenWidth=context.getResources().getDisplayMetrics().widthPixels;
        screenHeight=context.getResources().getDisplayMetrics().heightPixels;
        Log.v("TAG","-0-----width is 00"+screenWidth);
        Log.v("TAG","======height is ---"+screenHeight);
    }

    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) {
        Log.v("TAG","onIntercepHoverEvent ");
        return dragHelper.shouldInterceptTouchEvent(event);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.v("TAG","---widthMeasureSpec is "+widthMeasureSpec+"---heightmearsurespec is "+heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        int widthSizeSpec=MeasureSpec.getSize(widthMeasureSpec);
        int heightSizeSpec=MeasureSpec.getSize(heightMeasureSpec);
        int widthSpec=resolveSizeAndState(widthSizeSpec,widthMeasureSpec,0);
        int heightSpec=resolveSizeAndState(heightSizeSpec,heightMeasureSpec,0);
        Log.v("TAG","-----WIDTHSPEC IS "+widthSpec+"---heightspec is "+heightSpec);
        setMeasuredDimension(widthSpec,heightSpec);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mDragView1.layout(0, mTop, r, mDragView1.getMeasuredHeight() + mTop);
        mDragView2.layout(0,mTop+mDragView1.getMeasuredHeight(),r,b);

    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        dragHelper.processTouchEvent(event);
        switch (event.getAction()){
            case MotionEvent.ACTION_UP:
                        Log.v("TAG","---action up");
                /*if(dragHelper.smoothSlideViewTo(mDragView1, (int) (screenWidth / 2), screenHeight / 2)) {
                    ViewCompat.postInvalidateOnAnimation(CustomView.this);
                    Log.v("TAG","---action up1");

                }*/
                //dragHelper.settleCapturedViewAt(screenWidth/2,screenHeight/2);
               // invalidate();

                break;
            case MotionEvent.ACTION_DOWN:
                break;
        }
        return true;
    }

    class DragCallback extends ViewDragHelper.Callback{

        @Override
        public boolean tryCaptureView(View view, int i) {

            return  true;
        }

        @Override
        public void onViewDragStateChanged(int state) {
            super.onViewDragStateChanged(state);
            Log.v(TAG,"-------------------------1onViewDragStateChanged(int state) ");
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);
            mTop=top;
            Log.v("TAG","---TOP is "+top);

            Log.v(TAG,"-----------------2onViewPositionChanged ");
        }

        @Override
        public void onViewCaptured(View capturedChild, int activePointerId) {
            super.onViewCaptured(capturedChild, activePointerId);

            Log.v(TAG,"----------------33-onViewCaptured ");

        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            Log.v(TAG, "----------------4444---onViewReleased ");
            mDragView1.setScaleY(0.5f);
            mDragView1.setScaleX(0.5f);
            invalidate();

        }

        @Override
        public void onEdgeTouched(int edgeFlags, int pointerId) {
            super.onEdgeTouched(edgeFlags, pointerId);
            Log.v(TAG,"----------------555---onEdgeTouched");
        }

        @Override
        public boolean onEdgeLock(int edgeFlags) {
            Log.v(TAG,"----------------6666--onEdgeLock");
            return super.onEdgeLock(edgeFlags);

        }

        @Override
        public void onEdgeDragStarted(int edgeFlags, int pointerId) {
            super.onEdgeDragStarted(edgeFlags, pointerId);
            Log.v(TAG,"----------------77777-- onEdgeDragStarted ");
        }

        @Override
        public int getOrderedChildIndex(int index) {
            Log.v(TAG,"----------------8888---getOrderedChildIndex");
            return super.getOrderedChildIndex(index);
        }

        @Override
        public int getViewHorizontalDragRange(View child) {
            Log.v(TAG,"----------------999999---getViewHorizontalDragRange ");
            return super.getViewHorizontalDragRange(child);
        }

        @Override
        public int getViewVerticalDragRange(View child) {
            Log.v(TAG,"----------------1010----getViewVerticalDragRange ");
            return super.getViewVerticalDragRange(child);
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            Log.v(TAG,"----------------111111---clampViewPositionHorizontal ");
           /*// return super.clampViewPositionHorizontal(child, left, dx);
            int paddingLeft=getPaddingLeft();
           // int top=getTop();
            Log.v("TAG","------PADDING left IS "+paddingLeft+"== clampViewPositionHorizontal left is "+left);
           //当里面的View有padding值时的判断
            if(paddingLeft>left){
                Log.v("TAG","======PADDINGlEFT > left");
                return paddingLeft;
            }
            //当子view太宽时的判断
            if (mDragView1.getWidth()-getWidth()<left){
                return mDragView1.getWidth()-getWidth();
            }*/

            return left;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            /*Log.v(TAG,"----------------121212---clampViewPositionVertical");
           // return super.clampViewPositionVertical(child, top, dy);
            Log.v("TAG","======VERTICAL top IS "+top);
            //当子View有padding值时的情况
            int paddingTop=getPaddingTop();
            Log.v("TAG","----PADDINGTOP IS "+paddingTop);
            if(getPaddingTop()>top){
                Log.v("TAG","----PADINGTOP>TOP");
                return getPaddingTop();
            }
            //当子view太高时的情况
            if(mDragView1.getHeight()-getHeight()<top){
                Log.v("TAG","GETheight - getHeight <top");
                return mDragView1.getHeight()-getHeight();
            }*/
            return top;
        }
    }

    @Override
    public void computeScroll() {
        Log.v("TAG","=====computeScroll-");
        super.computeScroll();
        if(dragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }
}
