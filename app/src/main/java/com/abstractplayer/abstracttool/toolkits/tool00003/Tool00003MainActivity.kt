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
import com.abstractplayer.abstracttool.databinding.ActivityTool00003MainBinding
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

    private lateinit var binding: ActivityTool00003MainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTool00003MainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}