package com.abstractplayer.abstracttool.toolkits.tool00003.calendar;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

/**
 * * Created by 79260 at 2021/8/8 20:11.
 */
public class MonthPagerAdapter extends PagerAdapter {
    private boolean isUpdateMonthView = false;
    private Context context;

    public MonthPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return CalendarViewDelegate.maxYearCount;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = new MonthView(context);

        view.setTag(position);
        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return isUpdateMonthView ? POSITION_NONE : super.getItemPosition(object);
    }
}
