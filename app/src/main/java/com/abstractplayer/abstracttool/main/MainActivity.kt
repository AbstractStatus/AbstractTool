package com.abstractplayer.abstracttool.main


import android.content.pm.PackageManager

import android.os.Bundle

import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.abstractplayer.abstracttool.R

import com.abstractplayer.abstracttool.common.base.BaseActivity
import com.abstractplayer.abstracttool.databinding.ActivityMainBinding
import com.abstractplayer.abstracttool.main.fragment.MainPersonFragment
import com.abstractplayer.abstracttool.main.fragment.MainStatusFragment
import com.abstractplayer.abstracttool.main.fragment.MainToolFragment
import com.abstractplayer.abstracttool.main.tool.ToolListAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : BaseActivity(){
    private lateinit var binding: ActivityMainBinding
    private var fragmentList = arrayOf(MainToolFragment(), MainStatusFragment(), MainPersonFragment())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setBottomTab()
//        setBottomNavigation()
    }


    private fun setBottomTab(){
        binding.mainViewPager.adapter = object : FragmentStateAdapter(this){
            override fun getItemCount(): Int {
                return fragmentList.size
            }

            override fun createFragment(position: Int): Fragment {
                return fragmentList[position]
            }
        }

        val tabLayoutMediator = TabLayoutMediator(
                binding.mainTabLayout,
                binding.mainViewPager,
                TabLayoutMediator.TabConfigurationStrategy(
                        function = {tab: TabLayout.Tab, position: Int ->
                            when(position){
                                0 -> tab.icon = getDrawable(R.drawable.icon_main_tool)
                                1 -> tab.icon = getDrawable(R.drawable.icon_main_status)
                                else -> tab.icon = getDrawable(R.drawable.icon_main_person)
                            }

                            tab.text = when(position){
                                0 -> "工具"
                                1 -> "状态"
                                else -> "个体"
                            }
                        }
                ))

        tabLayoutMediator.attach()

    }



//    private fun setBottomNavigation(){
//        val navController = Navigation.findNavController(this, R.id.main_nav_fragment)
//        NavigationUI.setupWithNavController(binding.mainBottomNav, navController)
//        binding.mainBottomNav.selectedItemId = R.id.mainStatusFragment
//    }


//    override fun onBackPressed() {
//        super.onBackPressed()
//        finish()
//    }



}