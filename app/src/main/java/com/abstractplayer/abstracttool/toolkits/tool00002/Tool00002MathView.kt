package com.abstractplayer.abstracttool.toolkits.tool00002

import android.app.Activity
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.withTranslation


class Tool00002MathView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    companion object{
        const val TAG: String = "Tool00002MathView"
    }

    private var mWidth = 0f
    private var mHeight = 0f
    private var bigCellLength = 0f
    private var smallCellLength = 0f

    private val marginEdge = 20f

    private var touchIndexX: Int = 0
    private var touchIndexY: Int = 0

    private var selectIndexX: Int = 0
    private var selectIndexY: Int = 0

    private var eventX: Float = 0f
    private var eventY: Float = 0f

    private var digitList = Array(9) {Array(9){ it -> 0 }}

    //外边界画笔
    private val outerLinesPaint = Paint().apply {
        color = Color.WHITE
        strokeWidth = 8f
        style = Paint.Style.STROKE
    }

    //内线画笔
    private val innerLinesPaint = Paint().apply {
        color = Color.GRAY
        strokeWidth = 3f
        style = Paint.Style.STROKE
    }

    //输入数字画笔
    private val inputTextPaint = Paint().apply {
        color = Color.WHITE
        textSize = 50f
        typeface = Typeface.DEFAULT_BOLD
        textAlign = Paint.Align.CENTER
    }

    //输出数字画笔
    private val outputTextPaint = Paint().apply {
        color = Color.YELLOW
        textSize = 50f
        typeface = Typeface.DEFAULT_BOLD
        textAlign = Paint.Align.CENTER
    }

    init {

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val viewSize = getDefaultSize(suggestedMinimumWidth, widthMeasureSpec);
        setMeasuredDimension(viewSize, viewSize);
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w.toFloat() - marginEdge * 2
        mHeight = mWidth
        bigCellLength = mWidth / 3
        smallCellLength = mWidth / 9
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawColor(Color.BLACK)

        canvas?.apply {
            //画大格子边框
            drawOuterLines(canvas)
            //画小格子边框
            drawInnerLines(canvas)
            //写数字
            drawDigits(canvas)
        }
    }

    private fun drawOuterLines(canvas: Canvas?){
        canvas?.withTranslation(marginEdge, marginEdge){
            //画横线
            drawLine(0f, 0f, mWidth, 0f, outerLinesPaint)
            drawLine(0f, bigCellLength, mWidth, bigCellLength, outerLinesPaint)
            drawLine(0f, bigCellLength * 2, mWidth, bigCellLength * 2, outerLinesPaint)
            drawLine(0f, bigCellLength * 3, mWidth, bigCellLength * 3, outerLinesPaint)

            //画竖线
            drawLine(0f, 0f, 0f, mWidth, outerLinesPaint)
            drawLine(bigCellLength, 0f, bigCellLength, mWidth, outerLinesPaint)
            drawLine(bigCellLength * 2, 0f, bigCellLength * 2, mWidth, outerLinesPaint)
            drawLine(bigCellLength * 3, 0f, bigCellLength * 3, mWidth, outerLinesPaint)
        }
    }

    private fun drawInnerLines(canvas: Canvas?){
        canvas?.withTranslation(marginEdge, marginEdge){
            //每个大格子画两条，一共六条
            repeat(6){
                //画横线
                drawLine(0f, (it + (1 + (it / 2))) * smallCellLength, mWidth, (it + (1 + (it / 2))) * smallCellLength, innerLinesPaint)

                //画竖线
                drawLine((it + (1 + (it / 2))) * smallCellLength, 0f, (it + (1 + (it / 2))) * smallCellLength, mWidth, innerLinesPaint)
            }
        }
    }

    private fun drawDigits(canvas: Canvas?){
        canvas?.withTranslation(marginEdge, marginEdge){
            repeat(9 * 9){
                if(digitList[it % 9][it / 9] != 0){
                    val fontMetrics: Paint.FontMetrics = inputTextPaint.fontMetrics
                    val distance = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom
                    drawText(digitList[it % 9][it / 9].toString(), smallCellLength * (it % 9) + smallCellLength / 2
                                , smallCellLength * (it / 9) + smallCellLength / 2 + distance, inputTextPaint)
                }
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event != null) {
            eventX = event.x
        }
        if (event != null) {
            eventY = event.y
        }
        when(event?.action){
            MotionEvent.ACTION_DOWN -> {
                touchIndexX = ((eventX - marginEdge) / smallCellLength).toInt()
                touchIndexY = ((eventY - marginEdge) / smallCellLength).toInt()
                Log.d(TAG, "onTouchEvent: $x + $y")
                return true
            }
            MotionEvent.ACTION_UP -> {
                if(((eventX - marginEdge) / smallCellLength).toInt() == touchIndexX
                        && ((eventY - marginEdge) / smallCellLength).toInt() == touchIndexY
                        && touchIndexX >= 0 && touchIndexX < 9
                        && touchIndexY >= 0 && touchIndexY < 9){
                    selectIndexX = touchIndexX
                    selectIndexY = touchIndexY
                    val mathDialog = Tool00002MathDialog(this, selectIndexX, selectIndexY)
                    mathDialog.show((context as AppCompatActivity).supportFragmentManager, TAG)
                    return true
                }
            }
        }
        return super.onTouchEvent(event)
    }


    fun setDigitsList(indexX: Int, indexY: Int, num: Int){
        digitList[indexX][indexY] = num
        invalidate()
    }

    fun clearDigitsList(){
        repeat(81){
            digitList[it % 9][it / 9] = 0
        }

        invalidate()
    }

    fun solveMathView(){
        digitList = Tool00002Util.solveMathView(digitList)
        invalidate()
    }




}