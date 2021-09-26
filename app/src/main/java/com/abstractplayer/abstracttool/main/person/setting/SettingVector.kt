package com.abstractplayer.abstracttool.main.person.setting

import android.content.Context
import android.content.Intent
import com.abstractplayer.abstracttool.common.utils.SysServiceTool
import com.abstractplayer.abstracttool.main.person.MainPersonSettingActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.tencent.mmkv.MMKV


/**
 ** Created by AbstractStatus at 2021/9/12 13:43.
 */
object SettingVector {
    const val TAG = "SettingVector"
    //若某个设置挂靠在某个页面，则这个设置的十六进制为页面的十六进制左移4位再加上当前页的索引
    //按照这一规则，每个页面的设置（或者item数量）不超过16个
    private lateinit var context: Context

    fun initVector(context: Context){
        this.context = context
    }

    val entityVector = HashMap<Int, List<SettingEntity>>().apply {
        //root节点
        put(0x00, ArrayList<SettingEntity>().apply {
            add(SettingEntity(
                    0x01,
                    SettingEntity.TYPE_VECTOR,
                    "视觉设置",
                    SettingVectorData(0x10)
                )
            )
        })

        put(0x10, ArrayList<SettingEntity>().apply {
            add(SettingEntity(
                    0x11,
                    SettingEntity.TYPE_BOOLEAN,
                    if(MMKV.defaultMMKV().decodeBool(SettingKey.KEY_BOOLEAN_DAY_NIGHT_MODE))
                        "当前为黑夜模式"
                    else "当前为白天模式",
                    SettingBooleanData(MMKV.defaultMMKV().decodeBool(SettingKey.KEY_BOOLEAN_DAY_NIGHT_MODE))
            ))
        })
    }

    //坑点，这样写有问题，put之后返回的是上一个value，可能为空，要分开写，但是分开写又不优雅
//    val actionVector = HashMap<Int, HashMap<Int, () -> Unit>>().apply {
//        put(0x00, HashMap())?.apply {
//            put(0x01) {
//
//            }
//        }
//    }
    val actionVector = HashMap<Int, () -> Unit>().apply {
        put(0x00){

        }

        put(0x01){
            val intent = Intent(context, MainPersonSettingActivity::class.java)
            intent.putExtra(MainPersonSettingActivity.KEY_INTENT_PAGE_CODE, 0X10)
            intent.putExtra(MainPersonSettingActivity.KEY_INTENT_PAGE_TITLE, entityVector[0x01 shr 1]?.get(0x01 and 0x01 - 1)?.settingName)
            context.startActivity(intent)
        }

        put(0x11){

            MaterialAlertDialogBuilder(context)
                    .setTitle("黑白模式切换")
                    .setMessage("${entityVector[0x10]?.get(0)?.settingName}" +
                            "\n是否切换成")
                    .setPositiveButton("确定") { dialogInterface, i ->
                        var dayNightModeState = MMKV.defaultMMKV().decodeBool(SettingKey.KEY_BOOLEAN_DAY_NIGHT_MODE)
                        dayNightModeState = !dayNightModeState
                        MMKV.defaultMMKV().encode(SettingKey.KEY_BOOLEAN_DAY_NIGHT_MODE, dayNightModeState)
                    }
                    .setNegativeButton("取消") { dialogInterface, i -> }
                    .show()
        }
    }

}