package com.abstractplayer.abstracttool.main


import android.content.pm.PackageManager

import android.os.Bundle

import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abstractplayer.abstracttool.R

import com.abstractplayer.abstracttool.common.base.BaseActivity
import com.abstractplayer.abstracttool.databinding.ActivityMainBinding
import com.abstractplayer.abstracttool.main.tool.ToolListAdapter


class MainActivity : BaseActivity(){
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setBottomNavigation()
    }



    private fun setBottomNavigation(){
        val navController = Navigation.findNavController(this, R.id.main_nav_fragment)
        NavigationUI.setupWithNavController(binding.mainBottomNav, navController)
        binding.mainBottomNav.selectedItemId = R.id.mainStatusFragment
    }



}