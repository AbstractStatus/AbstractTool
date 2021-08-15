package com.abstractplayer.abstracttool.toolkits.tool00003.haha

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.abstractplayer.abstracttool.toolkits.tool00003.calendar.CalendarUtil

/**
 ** Created by 79260 at 2021/8/4 17:19.
 */
class MonthView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    
    companion object{
        const val TAG = "MonthView"
    }

    private val colors = arrayOf(Color.BLUE, Color.WHITE, Color.TRANSPARENT, Color.YELLOW)

    private var lockAnimator = false
    private var isOpen = false

    //事件触摸的x, y格子坐标
    private var touchIndexX = 0
    private var touchIndexY = 0

    //所打开的x, y格子坐标
    private var selectIndexX = 0
    private var selectIndexY = 0
    private var openPercent = 0f

    private var mWidth = 0f
    private var mHeight = 0f

    private var averageWidth = 0f
    private var averageHeight = 0f

    private var scheduleLists: ArrayList<ArrayList<ScheduleEntity>> = ArrayList()
    private var monthNumbers: ArrayList<Int> = ArrayList()
    private var thisMonth = 0;
    private var thisYear = 0

    private lateinit var onItemSelectListener: OnItemSelectListener


    private val dayCellBgPaint = Paint().apply {
        style = Paint.Style.FILL
    }

    private val dayCellTextPaint = Paint().apply {
        textSize = 50f
        textAlign = Paint.Align.CENTER
        color = Color.BLACK
    }


    init{

    }

    fun setSchedule(scheduleLists: ArrayList<ArrayList<ScheduleEntity>>){
        this.scheduleLists = scheduleLists
        invalidate()
    }

    fun setYearAndMonth(year: Int, month: Int){
        thisMonth = month
        thisYear = year
        val tmp =
            CalendarUtil.get42DaysDataByYearAndMonth(
                year,
                month
            )
        repeat(42){
            monthNumbers.add(tmp[it][2])
        }
        invalidate()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val measuredWidth = getDefaultSize(suggestedMinimumWidth, widthMeasureSpec);
        setMeasuredDimension(measuredWidth, measuredWidth);

        val measuredHeight = getDefaultSize(suggestedMinimumHeight, heightMeasureSpec);
        setMeasuredDimension(measuredHeight, measuredHeight);

        if(measuredWidth == 0 || measuredHeight == 0){
            val size = measuredWidth.coerceAtLeast(measuredHeight)
            setMeasuredDimension(size, size);
        }

        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        mWidth = w.toFloat()
        mHeight = h.toFloat()

        averageWidth = mWidth / 7
        averageHeight = mHeight / 6
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawColor(Color.TRANSPARENT)

        canvas.apply {
            drawCellsBg(this)
            drawCellText(this)
        }
    }


    private fun drawCellsBg(canvas: Canvas?){
        for(i in 0..selectIndexY){
            for(j in 0..6){
                dayCellBgPaint.color = Color.WHITE
                canvas?.drawRect(averageWidth * j, averageHeight * i - openPercent * selectIndexY * averageHeight,
                        averageWidth * (j + 1), averageHeight * (i + 1) - openPercent * selectIndexY * averageHeight,
                        dayCellBgPaint)
            }
        }

        for(i in (selectIndexY + 1)..5){
            for(j in 0..6){
                dayCellBgPaint.color = colors.random()
                canvas?.drawRect(averageWidth * j, averageHeight * i + openPercent * (6 - selectIndexY - 2) * averageHeight,
                        averageWidth * (j + 1), averageHeight * (i + 1) + openPercent * (6 - selectIndexY - 2)* averageHeight,
                        dayCellBgPaint)
            }
        }
    }


    private fun drawCellText(canvas: Canvas?){
        dayCellTextPaint.textSize = 50f
        val fontMetrics: Paint.FontMetrics = dayCellTextPaint.fontMetrics
        val distance = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom

        for(i in 0..selectIndexY){
            for(j in 0..6){
                canvas?.drawText(
                    if (monthNumbers.size != 0) monthNumbers[i * 7 + j].toString() else j.toString(),
                        (averageWidth * (0.5 + j)).toFloat(),
                        averageHeight * i + distance * 2 - openPercent * selectIndexY * averageHeight,
                        dayCellTextPaint)
            }
        }

        for(i in (selectIndexY + 1)..5){
            for(j in 0..6){
                canvas?.drawText(
                        if (monthNumbers.size != 0) monthNumbers[i * 7 + j].toString() else j.toString(),
                        (averageWidth * (0.5 + j % 7)).toFloat(),
                        averageHeight * i + distance * 2 + openPercent * (6 - selectIndexY - 2) * averageHeight,
                        dayCellTextPaint)
            }
        }

        dayCellTextPaint.textSize = 300f
        canvas?.drawText(thisYear.toString() + thisMonth.toString(), mWidth / 2,
            mHeight / 2,
            dayCellTextPaint)

    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {

        when(event?.action){
            MotionEvent.ACTION_DOWN -> {
                touchIndexX = (event.x / averageWidth).toInt()
                touchIndexY = (event.y / averageHeight).toInt()

                if(isOpen){
                    //不在打开后的日期区域，不响应触摸事件
                    if(!((selectIndexY == 5 && touchIndexY == 0)
                            || (selectIndexY != 5) && (touchIndexY == 0 || touchIndexY == 5))){
                        return false
                    }
                }
                return true
            }

            MotionEvent.ACTION_UP -> {
                if(lockAnimator){
                    return true
                }
                if(isClick(event.x, event.y)){
                    touchIndexX = (event.x / averageWidth).toInt()
                    touchIndexY = (event.y / averageHeight).toInt()

                    if(!isOpen){
                        selectIndexY = touchIndexY
                        selectIndexX = touchIndexX
                        startOpen()
                        onItemSelectListener.onItemSelect(selectIndexX, selectIndexY)
                    }
                    else{
                        //打开后点击的不是第一行
                        if(touchIndexY != 0){
                            startCloseBeforeOpen(touchIndexX, selectIndexY + 1)
                            onItemSelectListener.onItemSelect(touchIndexX, selectIndexY + 1)
                        }
                        //同一行不同列
                        else if(touchIndexY == 0 && touchIndexX != selectIndexX){
                            onItemSelectListener.onItemSelect(touchIndexX, selectIndexY)
                        }
                        //完全一样的坐标
                        else{
                            startClose()
                            onItemSelectListener.onItemSelect(selectIndexX, selectIndexY)
                        }
                    }

                }

                return true
            }
        }


        return super.onTouchEvent(event)
    }

    private fun setPercent(percent: Float){
        this.openPercent = percent
        postInvalidate()
    }


    private fun startOpen(){
        lockAnimator = true
        val animator = ValueAnimator.ofFloat(0f, 1f)
        animator.duration = 500
        animator.addUpdateListener {
            setPercent(it.animatedValue as Float)
        }

        animator.addListener(object : Animator.AnimatorListener{
            override fun onAnimationRepeat(animation: Animator?) {

            }

            override fun onAnimationEnd(animation: Animator?) {
                lockAnimator = false
                isOpen = true
            }

            override fun onAnimationCancel(animation: Animator?) {

            }

            override fun onAnimationStart(animation: Animator?) {

            }

        })
        animator.start()
    }


    private fun startClose(){
        lockAnimator = true
        val animator = ValueAnimator.ofFloat(1f, 0f)
        animator.duration = 500
        animator.addUpdateListener {
            setPercent(it.animatedValue as Float)
        }

        animator.addListener(object : Animator.AnimatorListener{
            override fun onAnimationRepeat(animation: Animator?) {

            }

            override fun onAnimationEnd(animation: Animator?) {
                lockAnimator = false
                isOpen = false
            }

            override fun onAnimationCancel(animation: Animator?) {

            }

            override fun onAnimationStart(animation: Animator?) {

            }

        })
        animator.start()
    }


    private fun startCloseBeforeOpen(newOpenXIndex: Int, newOpenYIndex: Int){
        lockAnimator = true
        val animator = ValueAnimator.ofFloat(1f, 0f)
        animator.duration = 500
        animator.addUpdateListener {
            setPercent(it.animatedValue as Float)
        }

        animator.addListener(object : Animator.AnimatorListener{
            override fun onAnimationRepeat(animation: Animator?) {

            }

            override fun onAnimationEnd(animation: Animator?) {
                selectIndexX = newOpenXIndex
                selectIndexY = newOpenYIndex
                startOpenAfterClose()
            }

            override fun onAnimationCancel(animation: Animator?) {

            }

            override fun onAnimationStart(animation: Animator?) {

            }

        })
        animator.start()
    }


    private fun startOpenAfterClose(){
        val animator = ValueAnimator.ofFloat(0f, 1f)
        animator.duration = 500
        animator.addUpdateListener {
            setPercent(it.animatedValue as Float)
        }

        animator.addListener(object : Animator.AnimatorListener{
            override fun onAnimationRepeat(animation: Animator?) {

            }

            override fun onAnimationEnd(animation: Animator?) {
                lockAnimator = false
            }

            override fun onAnimationCancel(animation: Animator?) {

            }

            override fun onAnimationStart(animation: Animator?) {

            }

        })
        animator.start()
    }


    private fun isClick(x: Float, y: Float) : Boolean{
        return (x / averageWidth).toInt() == touchIndexX
                && (y / averageHeight).toInt() == touchIndexY

    }

    public interface OnItemSelectListener{
        fun onItemSelect(itemIndexX: Int, itemIndexY: Int)
    }

}