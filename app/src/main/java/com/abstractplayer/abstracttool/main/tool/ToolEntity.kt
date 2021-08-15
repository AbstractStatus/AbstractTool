package com.abstractplayer.abstracttool.main.tool

import android.app.Activity
import android.view.View

//工具列表的数据实体
class ToolEntity(val info: ToolInfo, val activity: Class<out Activity>,
                 var visible: Int = View.VISIBLE) {

}