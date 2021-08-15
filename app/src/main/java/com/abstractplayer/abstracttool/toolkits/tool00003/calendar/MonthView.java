package com.abstractplayer.abstracttool.toolkits.tool00003.calendar;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * * Created by 79260 at 2021/8/8 20:11.
 */
public class MonthView extends View {
    private static final String TAG = "MonthView";

    private int[] colors = new int[]{Color.BLUE, Color.WHITE, Color.TRANSPARENT, Color.YELLOW};
    private Random random = new Random();

    private boolean lockAnimator = false;
    private boolean isOpen = false;

    //事件触摸的x, y格子坐标
    private int touchIndexX = 0;
    private int touchIndexY = 0;

    //所打开的x, y格子坐标
    private int selectIndexX = 0;
    private int selectIndexY = 0;
    private float openPercent = 0f;

    private float mWidth = 0f;
    private float mHeight = 0f;

    private float averageWidth = 0f;
    private float averageHeight = 0f;

    private List<ArrayList<ScheduleEntity>> scheduleLists = new ArrayList();
    private int[][] dates;
    private int thisMonth = 0;
    private int thisYear = 0;

    private OnItemSelectListener onItemSelectListener;


    private Paint dayCellBgPaint = new Paint();

    private Paint dayCellTextPaint = new Paint();


    public MonthView(Context context) {
        super(context);
        init(context);
    }


    public MonthView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    public MonthView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    public MonthView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }


    private void init(Context context){
        dayCellBgPaint.setStyle(Paint.Style.FILL);

        dayCellTextPaint.setTextSize(50f);
        dayCellTextPaint.setTextAlign(Paint.Align.CENTER);
        dayCellTextPaint.setColor(Color.BLACK);
    }


    public void setScheduleLists(List<ArrayList<ScheduleEntity>> scheduleLists) {
        this.scheduleLists = scheduleLists;
        invalidate();
    }


    public void setYearAndMonth(int year, int month){
        thisMonth = month;
        thisYear = year;
        dates = CalendarUtil.get42DaysDataByYearAndMonth(year, month);
        invalidate();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int measuredWidth = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        setMeasuredDimension(measuredWidth, measuredWidth);

        int measuredHeight = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        setMeasuredDimension(measuredHeight, measuredHeight);

        if(measuredWidth == 0 || measuredHeight == 0){
            int size = Math.max(measuredWidth, measuredHeight);
            setMeasuredDimension(size, size);
        }

        setMeasuredDimension(measuredWidth, measuredHeight);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;

        averageWidth = mWidth / 7;
        averageHeight = mHeight / 6;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.TRANSPARENT);

        drawCellsBg(canvas);
    }


    private void drawCellsBg(Canvas canvas){
        for(int i = 0; i <= selectIndexY; i++){
            for(int j = 0; j < 7; j++){
                dayCellBgPaint.setColor(Color.WHITE);
                canvas.drawRect(averageWidth * j, averageHeight * i - openPercent * selectIndexY * averageHeight,
                        averageWidth * (j + 1), averageHeight * (i + 1) - openPercent * selectIndexY * averageHeight,
                        dayCellBgPaint);
            }
        }

        for(int i = selectIndexY + 1; i < 6; i++){
            for(int j = 0; j < 7; j++){
                dayCellBgPaint.setColor(colors[random.nextInt(colors.length)]);
                canvas.drawRect(averageWidth * j, averageHeight * i + openPercent * (6 - selectIndexY - 2) * averageHeight,
                        averageWidth * (j + 1), averageHeight * (i + 1) + openPercent * (6 - selectIndexY - 2)* averageHeight,
                        dayCellBgPaint);
            }
        }
    }


    private void drawCellText(Canvas canvas){
        dayCellTextPaint.setTextSize(50f);
        Paint.FontMetrics fontMetrics = dayCellTextPaint.getFontMetrics();
        float distance = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;

        for(int i = 0; i <= selectIndexY; i++){
            for(int j = 0; j < 7; j++){
                canvas.drawText(
                String.valueOf(dates.length != 0 ? dates[i * 7 + j][2] : 0),
                        (float) (averageWidth * (0.5 + j)),
                        averageHeight * i + distance * 2 - openPercent * selectIndexY * averageHeight,
                        dayCellTextPaint);
            }
        }

        for(int i = selectIndexY + 1; i < 6; i++){
            for(int j = 0; j < 7; j++){
                canvas.drawText(
                        String.valueOf(dates.length != 0 ? dates[i * 7 + j][2] : 0),
                        (float) (averageWidth * (0.5 + j % 7)),
                        averageHeight * i + distance * 2 + openPercent * (6 - selectIndexY - 2) * averageHeight,
                        dayCellTextPaint);
            }
        }

        dayCellTextPaint.setTextSize(200f);
        canvas.drawText(String.valueOf(thisYear) + thisMonth, mWidth / 2,
                mHeight / 2,
                dayCellTextPaint);

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN : {
                touchIndexX = (int) (event.getX() / averageWidth);
                touchIndexY = (int) (event.getY() / averageHeight);

                if(isOpen){
                    //不在打开后的日期区域，不响应触摸事件
                    if(!((selectIndexY == 5 && touchIndexY == 0)
                            || (selectIndexY != 5) && (touchIndexY == 0 || touchIndexY == 5))){
                        return false;
                    }
                }
                return true;
            }

            case MotionEvent.ACTION_UP: {
                if(lockAnimator){
                    return true;
                }
                if(isClick(event.getX(), event.getY())){
                    touchIndexX = (int) (event.getX() / averageWidth);
                    touchIndexY = (int) (event.getY() / averageHeight);

                    if(!isOpen){
                        selectIndexY = touchIndexY;
                        selectIndexX = touchIndexX;
                        startOpen();
                        onItemSelectListener.onItemSelect(selectIndexX, selectIndexY);
                    }
                    else{
                        //打开后点击的不是第一行
                        if(touchIndexY != 0){
                            startCloseBeforeOpen(touchIndexX, selectIndexY + 1);
                            onItemSelectListener.onItemSelect(touchIndexX, selectIndexY + 1);
                        }
                        //同一行不同列
                        else if(touchIndexY == 0 && touchIndexX != selectIndexX){
                            onItemSelectListener.onItemSelect(touchIndexX, selectIndexY);
                        }
                        //完全一样的坐标
                        else{
                            startClose();
                            onItemSelectListener.onItemSelect(selectIndexX, selectIndexY);
                        }
                    }

                }

                return true;
            }
        }

        return super.onTouchEvent(event);
    }


    private void setPercent(float percent){
        this.openPercent = percent;
        postInvalidate();
    }


    private void startOpen(){
        lockAnimator = true;
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);
        animator.setDuration(500);
        animator.addUpdateListener(animation -> {
            setPercent((Float) animation.getAnimatedValue());
        });

        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                lockAnimator = false;
                isOpen = true;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();
    }


    private void startClose(){
        lockAnimator = true;
        ValueAnimator animator = ValueAnimator.ofFloat(1f, 0f);
        animator.setDuration(500);
        animator.addUpdateListener(animation -> {
            setPercent((Float) animation.getAnimatedValue());
        });

        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                lockAnimator = false;
                isOpen = false;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();
    }


    private void startCloseBeforeOpen(int newOpenXIndex, int newOpenYIndex){
        lockAnimator = true;
        ValueAnimator animator = ValueAnimator.ofFloat(1f, 0f);
        animator.setDuration(500);
        animator.addUpdateListener(animation -> {
            setPercent((Float) animation.getAnimatedValue());
        });

        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                selectIndexX = newOpenXIndex;
                selectIndexY = newOpenYIndex;
                startOpenAfterClose();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();
    }


    private void startOpenAfterClose(){
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);
        animator.setDuration(500);
        animator.addUpdateListener(animation -> {
            setPercent((Float) animation.getAnimatedValue());
        });

        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                lockAnimator = false;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();
    }


    private boolean isClick(float x, float y){
        return (x / averageWidth) == touchIndexX
                && (y / averageHeight) == touchIndexY;

    }



    public interface OnItemSelectListener{
        void onItemSelect(int itemIndexX, int itemIndexY);
    }
}
