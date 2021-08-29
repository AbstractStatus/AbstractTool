package com.abstractplayer.abstracttool.common.view.paint

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import kotlin.math.abs

/**
 ** Created by 79260 at 2021/8/19 21:46.
 */
class CommonPatternPwdView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    companion object{
        const val TAG = "CommonPatternPwdView"
    }

    lateinit var onSuccess: () -> Unit
    lateinit var onError: () -> Unit

    private var mWidth = 0f
    private var mHeight = 0f

    private var circleRadius = 0f

    private var marginDimen = 0f
    private var paddingDimen = 0f

    private var curX: Float = 0f
    private var curY: Float = 0f

    private var isCompletePass = false

    private var circleXY = arrayOf(0f, 0f, 0f)
    private var hasPassArr = BooleanArray(9)

    //0~8，从左往右，再从上到下
    var pwdList = ArrayList<Int>().apply {
        add(2)
        add(1)
        add(0)
        add(4)
        add(6)
        add(7)
        add(8)
    }
    private var passList = ArrayList<Int>()

    private val circleRawPaint = Paint().apply {
        color = Color.WHITE
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
        isAntiAlias = true
    }

    private val circlePassPaint = Paint().apply {
        color = Color.WHITE
        style = Paint.Style.FILL_AND_STROKE
        strokeCap = Paint.Cap.ROUND
        isAntiAlias = true
    }

    private val linePaint = Paint().apply {
        color = Color.WHITE
        style = Paint.Style.STROKE
        strokeWidth = 5f
        strokeCap = Paint.Cap.ROUND
        isAntiAlias = true
    }

    init {

    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val measuredWidth = getDefaultSize(suggestedMinimumWidth, widthMeasureSpec)
        val measuredHeight = getDefaultSize(suggestedMinimumHeight, heightMeasureSpec)
        if (measuredWidth == measuredHeight) {
            setMeasuredDimension(measuredWidth, measuredHeight)
        }
        setMeasuredDimension(measuredWidth, measuredWidth)
    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w.toFloat()
        mHeight = h.toFloat()

        //3*3个圆圈，每个维度总共6条半径、2个内边距、2个外边距
        //上述长度比例按照1：3：3进行分配，则总共1*6 + 3*2 + 3*2 = 18
        //则上述长度分别为：
        circleRadius = mWidth / 18
        paddingDimen = mWidth / 6
        marginDimen = mWidth / 6

        repeat(3){
            circleXY[it] = marginDimen + it * (2 * circleRadius + paddingDimen) + circleRadius
        }
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        drawRawCircle(canvas)
        drawPassCircle(canvas)
        drawLineConnect(canvas)
    }


    private fun drawRawCircle(canvas: Canvas?){
        repeat(3){i ->
            repeat(3){j ->
                canvas?.drawCircle(
                        circleXY[i],
                        circleXY[j],
                        circleRadius,
                        circleRawPaint
                )
            }
        }
    }


    private fun drawPassCircle(canvas: Canvas?){
        for(i in passList){
            canvas?.drawCircle(
                    circleXY[i % 3],
                    circleXY[i / 3],
                    circleRadius,
                    circlePassPaint
            )
        }
    }


    private fun drawLineConnect(canvas: Canvas?){
        if(passList.size == 0){
            return
        }

        if(passList.size > 1){
            for(i in 0..(passList.size - 2)){
                canvas?.drawLine(
                        circleXY[passList[i] % 3],
                        circleXY[passList[i] / 3],
                        circleXY[passList[(i + 1)] % 3],
                        circleXY[passList[(i + 1)] / 3],
                        linePaint
                )
            }
        }

        if(!isCompletePass){
            canvas?.drawLine(
                    circleXY[passList[passList.size - 1] % 3],
                    circleXY[passList[passList.size - 1] / 3],
                    curX,
                    curY,
                    linePaint
            )
        }

    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val x = event?.x ?: 0
        val y = event?.y ?: 0
        when(event?.action){
            MotionEvent.ACTION_DOWN -> {
                resetView()
                return true
            }

            MotionEvent.ACTION_MOVE -> {
                Log.d(TAG, "onTouchEvent: ")
                if(isInCircle(x as Float, y as Float)){
                    Log.d(TAG, "onTouchEvent: aaa")
                    invalidate()
                }
                if(!isCompletePass){
                    curX = x
                    curY = y
                    invalidate()
                }
                return true
            }

            MotionEvent.ACTION_UP -> {
                Log.d(TAG, "onTouchEvent: up")
                if(checkPassword()){
                    onSuccess()
                }
                else{
                    onError()
                    linePaint.color = Color.RED
                    circlePassPaint.color = Color.RED
                }
                isCompletePass = true
                invalidate()
                return true
            }
        }

        return super.onTouchEvent(event)
    }


    private fun isInCircle(x: Float, y: Float): Boolean{
        repeat(3){i ->
            repeat(3){j ->
                if(abs(x - circleXY[i]) <= circleRadius
                        && abs(y - circleXY[j]) <= circleRadius){
                    if(hasPassArr[i + j * 3]){
                        return true
                    }
                    passList.add(i + j * 3)
                    hasPassArr[i + j * 3] = true
                    return true

                }
            }
        }
        return false
    }

    private fun checkPassword(): Boolean{
        if(passList.size != pwdList.size){
            return false
        }
        for(i in 0 until passList.size){
            if(passList[i] != pwdList[i]){
                Log.d(TAG, "checkPassword: $passList[i] + $pwdList[i]")
                return false
            }
        }
        return true
    }

    private fun resetView(){
        repeat(9){
            hasPassArr[it] = false
        }
        linePaint.color = Color.WHITE
        circlePassPaint.color = Color.WHITE
        passList.clear()
        isCompletePass = false
        invalidate()
    }
}