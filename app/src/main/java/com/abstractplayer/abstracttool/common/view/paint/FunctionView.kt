package com.abstractplayer.abstracttool.common.view.paint

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.withRotation
import androidx.core.graphics.withTranslation
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import kotlinx.coroutines.*
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

/**
 * @author AbstractPlayer
 * @since 2021-07-25
 * @see "bili" "longway777"
 * @describe 指数函数旋转矢量视图
 */

class FunctionView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr), LifecycleObserver {
    //函数路径
    private var sineWaveSamplePath = Path()
    //协程作业
    private var rotatingJob: Job? = null
    //矢量的旋转角度
    private var mAngle = 10f
    //矢量的长度，即圆的半径
    private var mRadius = 0f
    private val titleStr = "指数函数与旋转矢量"
    //视图的宽高
    private var mWidth = 0f
    private var mHeight = 0f
    //标准实线画笔
    private val solidLinePaint = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = 5f
        color = Color.WHITE
    }

    //文本画笔
    private val textPaint = Paint().apply {
        textSize = 50f
        typeface = Typeface.DEFAULT_BOLD
        color = Color.WHITE
    }

    //虚线画笔
    private val dashedLinePaint = Paint().apply {
        style = Paint.Style.STROKE
        pathEffect = DashPathEffect(floatArrayOf(10f, 10f), 0f)
        color = Color.YELLOW
    }

    //矢量的画笔
    private val vectorLinePaint = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = 5f
        color = Color.GREEN
    }

    //填充画笔
    private val fillCirclePaint = Paint().apply {
        style = Paint.Style.FILL
        color = Color.WHITE
    }

    //发生在onMeasure后, onLayout前
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w.toFloat()
        mHeight = h.toFloat()
        mRadius = if (w < h/2) {
            w/2.toFloat()
        } else {
            h/4.toFloat()
        } - 20f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        //黑色背景
        canvas?.drawColor(Color.BLACK)

        canvas?.apply {
            //绘制坐标轴
            drawAxises(this)
            //绘制标题
            drawTitle(this)
            //绘制虚线圆圈
            drawDashedCircle(this)
            //绘制旋转矢量直线
            drawVector(this)
            //绘制投影
            drawProjection(this)
            //绘制函数路径曲线
            drawSineWave(this)
        }
    }

    private fun drawAxises(canvas: Canvas?){
        canvas?.withTranslation (mWidth / 2, mHeight / 2){
            drawLine(-mWidth / 2, 0f, mWidth / 2, 0f, solidLinePaint)
            drawLine(0f, -mHeight / 2, 0f, mHeight / 2, solidLinePaint)
        }

        canvas?.withTranslation(mWidth / 2, mHeight * 3 / 4){
            drawLine(-mWidth / 2, 0f, mWidth / 2, 0f, solidLinePaint)
        }
    }

    private fun drawTitle(canvas: Canvas?){
        canvas?.apply{
            drawRect(100f, 100f, 600f, 250f, solidLinePaint)
            drawText(titleStr, 120f, 195f, textPaint)
        }
    }

    private fun drawDashedCircle(canvas: Canvas?){
        canvas?.withTranslation(mWidth / 2, mHeight * 3 / 4){
            drawCircle(0f, 0f, mRadius, dashedLinePaint)
        }
    }

    private fun drawVector(canvas: Canvas?){
        canvas?.withTranslation(mWidth / 2, mHeight * 3 / 4) {
            withRotation(-mAngle){
                drawLine(0f, 0f, mRadius, 0f, vectorLinePaint)
            }
        }
    }

    private fun drawProjection(canvas: Canvas?){
        canvas?.withTranslation(mWidth / 2, mHeight / 2){
            drawCircle(mRadius * cos(mAngle.toRadians()), 0f, 20f, fillCirclePaint)
        }
        canvas?.withTranslation(mWidth / 2, mHeight * 3 / 4){
            drawCircle(mRadius * cos(mAngle.toRadians()), 0f, 20f, fillCirclePaint)
        }
        canvas?.withTranslation(mWidth / 2, mHeight * 3 / 4){
            val x = mRadius * cos(mAngle.toRadians())
            val y = mRadius * sin(mAngle.toRadians())
            withTranslation(x, -y){
                drawLine(0f, 0f, 0f, y, solidLinePaint)
                drawLine(0f, 0f, 0f, -mHeight / 4 + y, dashedLinePaint)
            }
        }
    }

    private fun drawSineWave(canvas: Canvas?){
        canvas?.withTranslation(mWidth / 2, mHeight / 2){
            val sampleCount = 50
            val dy = mHeight / 2 / sampleCount
            sineWaveSamplePath.reset()
            sineWaveSamplePath.moveTo(mRadius * cos(mAngle.toRadians()), 0f)
            repeat(sampleCount){
                //it相当于三角函数的角速度
                val x = mRadius * cos(it * 0.15 + mAngle.toRadians())
                val y = -dy * it
                sineWaveSamplePath.quadTo(x.toFloat(), y, x.toFloat(), y)
            }
            drawPath(sineWaveSamplePath, vectorLinePaint)
            drawTextOnPath(titleStr, sineWaveSamplePath, 500f, 0f, textPaint)
        }
    }

    //需要在使用的地方添加lifecycle.addObserver(binding.funcView)
    //从而进行生命周期绑定
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun startRotating(){
        rotatingJob = CoroutineScope(Dispatchers.Main).launch{
            while (true){
                delay(100)
                mAngle += 5f
                invalidate()
            }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun pauseRotating(){
        rotatingJob?.cancel()
    }

    //扩展函数---角度转弧度
    private fun Float.toRadians() = this / 180 * PI.toFloat()
}