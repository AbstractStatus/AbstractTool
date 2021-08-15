package com.abstractplayer.abstracttool.toolkits.tool00003

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.abstractplayer.abstracttool.R
import com.abstractplayer.abstracttool.common.base.BaseActivity
import com.abstractplayer.abstracttool.toolkits.tool00003.calendar.*
import com.abstractplayer.abstracttool.toolkits.tool00003.haha.CalendarViewDelegate
import com.abstractplayer.abstracttool.toolkits.tool00003.haha.MonthPagerAdapter
import com.abstractplayer.abstracttool.toolkits.tool00003.haha.MonthView
import com.abstractplayer.abstracttool.toolkits.tool00003.haha.ScheduleEntity
import com.abstractplayer.abstracttool.toolkits.tool00003.haha.ScheduleListAdapter
import kotlin.collections.ArrayList

class Tool00003MainActivity : BaseActivity() {
    companion object{
        const val TAG: String = "Tool00003MainActivity"
    }

    private lateinit var vp: ViewPager
    private lateinit var vpAdapter: MonthPagerAdapter
    private lateinit var rv: RecyclerView
    private lateinit var rvAdapter: ScheduleListAdapter
    private var scheduleList = ArrayList<ScheduleEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tool00003_main)
        
        requestPermissions()

        vp = findViewById(R.id.vp_test)

        rv = findViewById(R.id.rv_test)
        rv.post {
            val averageHeight = rv.height / 6
            val lp = rv.layoutParams as ViewGroup.MarginLayoutParams
            lp.topMargin = averageHeight
            lp.bottomMargin = averageHeight
            lp.height = averageHeight * 4
            Log.d(TAG, "onCreate: $averageHeight")
            rv.layoutParams = lp
        }


        rvAdapter =
            ScheduleListAdapter()
        repeat(10){
            val scheduleEntity =
                ScheduleEntity(
                    it.toString(),
                    it.toString(),
                    it.toString()
                )
            scheduleList.add(scheduleEntity)
        }
        rv.adapter = rvAdapter
        rv.layoutManager = LinearLayoutManager(this)
        rvAdapter.scheduleList = scheduleList
        rvAdapter.notifyDataSetChanged()


        vpAdapter =
            MonthPagerAdapter(
                this
            )
        vp.adapter = vpAdapter
        vp.currentItem = CalendarViewDelegate.maxYearCount / 2


        vp.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                val yearAndMonth = CalendarUtil.getAddYearAndMonth(
                        CalendarUtil.getNowYearAndMonth()[0],
                        CalendarUtil.getNowYearAndMonth()[1],
                        vp.currentItem - CalendarViewDelegate.maxYearCount / 2
                )

                val view = vp.findViewWithTag<MonthView>(position)
                Log.d(TAG, "onPageSelected: $view")
                view.setYearAndMonth(yearAndMonth[0], yearAndMonth[1])
            }

        })

    }


    private fun requestPermissions(){
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_CALENDAR)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_CALENDAR), 1)
        }
        else{
            readCalendar()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            1 -> {
                if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    readCalendar()
                }
                else{
                    Toast.makeText(this, "你拒绝了读取日历权限", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun readCalendar(){

        val list: List<CalendarEvent> = CalendarProviderManager.queryAccountEvent(this, CalendarProviderManager.obtainCalendarAccountID(this));
        for (i in list){
            Log.d(TAG, "readCalendar: ${i.toString()}")
        }

//        val list: List<SystemCalendarRepository.SystemScheduleEntity> = SystemCalendarRepository.getScheduleData(contentResolver)
//        Log.d(TAG, "readCalendar: listSize: ${list.size}")
//        repeat(list.size){
//            Log.d(TAG, "readCalendar: ${list[it].toString()}")
//        }
    }
}