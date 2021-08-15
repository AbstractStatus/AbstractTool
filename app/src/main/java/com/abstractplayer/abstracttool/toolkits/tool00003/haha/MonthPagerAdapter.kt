package com.abstractplayer.abstracttool.toolkits.tool00003.haha

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter

/**
 ** Created by 79260 at 2021/8/6 18:06.
 */
class MonthPagerAdapter(val context: Context) : PagerAdapter(){
    private var isUpdateMonthView: Boolean = false

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return CalendarViewDelegate.maxYearCount
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view =
            MonthView(
                context
            )

        view.tag = position
        container.addView(view)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }

    override fun getItemPosition(`object`: Any): Int {
        return if (isUpdateMonthView) POSITION_NONE else super.getItemPosition(`object`)
    }
}