package com.abstractplayer.abstracttool.common.view.paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author abstractPlayer
 * 更新时间：2020-12-18
 * 功能：流量统计圆形图
 * 注意事项：
 * 1、使用时，务必先findViewById，然后set分数值
 */

public class WaterCircleView extends View {
    private static final String TAG = "WaterCircleView";
    private Paint paint;
    private float radius;
    private Path path;
    private int percent;
    private float float_percent;

    public float getFloat_percent() {
        return float_percent;
    }

    public void setFloat_percent(float float_percent) {
        this.float_percent = float_percent;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
        invalidate();
    }

    public WaterCircleView(Context context) {
        super(context);
        init();
    }

    public WaterCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WaterCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        paint = new Paint();
        path = new Path();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float_percent = (float) percent / 100;

        Log.d(TAG, "onDraw: " + getFloat_percent() / 100.0f);

        float this_width = getWidth();
        float this_height = getHeight();

        float left = getLeft();
        float top = getTop();
        float right = getRight();
        float bottom = getBottom();

        paint.setColor(0XEE11554C);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        radius = Math.min(this_width, this_height) * 2 / 5;
        float base_x = this_width / 2;
        float base_y = this_height / 2;
        canvas.drawCircle(base_x, base_y, radius, paint);

        paint.setTextSize(radius/2);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setColor(0XFF000000);
        paint.setStrokeWidth(3);
        canvas.drawText(String.valueOf((int) float_percent), base_x, base_y + radius/4, paint);

        paint.setColor(0X66FAE456);
        paint.setStyle(Paint.Style.FILL);

        if(getFloat_percent() >= 100){
            canvas.drawCircle(base_x, base_y, radius, paint);
            return ;
        }

        float d = getWaterDepth(getFloat_percent() / 100.0f);
        float start_x, start_y, end_x, end_y, temp_end_x, temp_end_y;
        float bottom_length;
        float average_radian = radius / 50;
        boolean state = (d < radius);

        bottom_length = (float) Math.sqrt(radius * radius - (radius - d) * (radius - d));
        start_x = base_x - bottom_length;
        end_x= base_x + bottom_length;
        start_y = getYByCircleX(start_x, base_x, base_y, state);
        end_y = getYByCircleX(end_x, base_x, base_y, state);

        path.moveTo(end_x, end_y);
        path.lineTo(start_x, start_y);


        if(state){
            temp_end_x = start_x + average_radian;
            temp_end_y = getYByCircleX(temp_end_x, base_x, base_y, true);
            while(temp_end_x < end_x){
                path.lineTo(temp_end_x, temp_end_y);

                temp_end_x += average_radian;
                temp_end_y = getYByCircleX(temp_end_x, base_x, base_y, true);
            }
        }
        else{
            temp_end_x = start_x - average_radian;
            temp_end_y = getYByCircleX(temp_end_x, base_x, base_y, false);
//            while(temp_end_x < end_x){
//                path.lineTo(temp_end_x, temp_end_y);

//                temp_end_x += average_radian;
//                temp_end_y = getYByCircleX(temp_end_x, base_x, base_y, true);
//            }
            while(temp_end_x > base_x - radius){
                path.lineTo(temp_end_x, temp_end_y);

                temp_end_x -= average_radian;
                temp_end_y = getYByCircleX(temp_end_x, base_x, base_y, false);
            }
            while(temp_end_x < base_x + radius){
                path.lineTo(temp_end_x, temp_end_y);

                temp_end_x += average_radian;
                temp_end_y = getYByCircleX(temp_end_x, base_x, base_y, true);
            }
            while(temp_end_x > end_x){
                path.lineTo(temp_end_x, temp_end_y);

                temp_end_x -= average_radian;
                temp_end_y = getYByCircleX(temp_end_x, base_x, base_y, false);
            }
        }

        canvas.drawPath(path, paint);
    }

    private float getYByCircleX(float x, float base_x, float base_y, boolean state){
        float temp = (float) Math.sqrt((radius * radius - (x - base_x) * (x - base_x)) > 0 ? (radius * radius - (x - base_x) * (x - base_x)) : 0);
        return state ? (float) (temp + base_y) :
                (float) (base_y - temp);
    }

    private float getWaterDepth(float waterVolumePercent){
        float d;
        float calculate_water_volume_percent;
        float temp_num;
        float start, end;
        if(waterVolumePercent <= 0.5){
            start = 0;
            end = radius;
            d = radius / 2;
            temp_num = get_temp_num(d, radius);
            calculate_water_volume_percent = get_radian_percent(temp_num, true) - get_temp_triangle_percent(d, radius, temp_num);
            while(Math.abs(calculate_water_volume_percent - waterVolumePercent) > 1e-4){
                Log.d("rong", "d " + d);
                Log.d("rong", "calculate_water_volume_percent: " + calculate_water_volume_percent);
                if(calculate_water_volume_percent < waterVolumePercent){
                    start = d;
                }
                else{
                    end = d;
                }
                d = (start + end) / 2;
                temp_num = get_temp_num(d, radius);
                calculate_water_volume_percent = get_radian_percent(temp_num, true) - get_temp_triangle_percent(d, radius, temp_num);
            }
        }
        else{
            start = radius;
            end = radius * 2;
            d = radius * 3 / 2;
            temp_num = get_temp_num(d, radius);
            calculate_water_volume_percent = get_radian_percent(temp_num, false) - get_temp_triangle_percent(d, radius, temp_num);
            while(Math.abs(calculate_water_volume_percent - waterVolumePercent) > 1e-4){
                if(calculate_water_volume_percent < waterVolumePercent){
                    start = d;
                }
                else{
                    end = d;
                }
                d = (start + end) / 2;
                temp_num = get_temp_num(d, radius);
                calculate_water_volume_percent = get_radian_percent(temp_num, false) - get_temp_triangle_percent(d, radius, temp_num);
            }
        }
        return d;
    }

    private float get_temp_num(float d, float r){
        return (float) (Math.sqrt(d * (2 * r - d)) / r);
    }

    private float get_temp_triangle_percent(float d, float r, float temp_num){
        return (float) ((r - d) * temp_num / (Math.PI * r));
    }

    private float get_radian_percent(float temp_num, boolean state){
        if(temp_num <= -1){
            return (float) (state ? Math.asin(-1) / Math.PI :
                    1 - (Math.asin(-1) / Math.PI));
        }
        else if(temp_num >= 1){
            return (float) (state ? Math.asin(1) / Math.PI :
                    1 - (Math.asin(1) / Math.PI));
        }
        return (float) (state ? Math.asin(temp_num) / Math.PI :
                1 - (Math.asin(temp_num) / Math.PI));
    }
}

