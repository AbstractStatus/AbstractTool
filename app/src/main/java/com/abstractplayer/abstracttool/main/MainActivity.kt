package com.abstractplayer.abstracttool.main


import android.content.pm.PackageManager

import android.os.Bundle

import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.abstractplayer.abstracttool.common.base.BaseActivity
import com.abstractplayer.abstracttool.databinding.ActivityMainBinding
import com.abstractplayer.abstracttool.main.tool.ToolListAdapter


class MainActivity : BaseActivity(){
    private lateinit var binding: ActivityMainBinding
    private val toolListAdapter = ToolListAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        lifecycle.addObserver(binding.funcView)
        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = toolListAdapter
        toolListAdapter.notifyDataSetChanged()

        setBottomNavigation()
    }



    private fun setBottomNavigation(){

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

    }

}