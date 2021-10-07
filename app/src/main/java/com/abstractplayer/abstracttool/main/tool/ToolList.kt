package com.abstractplayer.abstracttool.main.tool

import com.abstractplayer.abstracttool.toolkits.tool00001.Tool00001Info
import com.abstractplayer.abstracttool.toolkits.tool00001.Tool00001MainActivity
import com.abstractplayer.abstracttool.toolkits.tool00002.Tool00002Info
import com.abstractplayer.abstracttool.toolkits.tool00002.Tool00002MainActivity
import com.abstractplayer.abstracttool.toolkits.tool00003.Tool00003Info
import com.abstractplayer.abstracttool.toolkits.tool00003.Tool00003MainActivity
import com.abstractplayer.abstracttool.toolkits.tool00004.Tool00004Info
import com.abstractplayer.abstracttool.toolkits.tool00004.Tool00004MainActivity
import com.abstractplayer.abstracttool.toolkits.tool00005.Tool00005Info
import com.abstractplayer.abstracttool.toolkits.tool00005.Tool00005MainActivity
import com.abstractplayer.abstracttool.toolkits.tool00006.Tool00006Info
import com.abstractplayer.abstracttool.toolkits.tool00006.Tool00006MainActivity
import com.abstractplayer.abstracttool.toolkits.tool00007.Tool00007Info
import com.abstractplayer.abstracttool.toolkits.tool00007.Tool00007MainActivity
import com.abstractplayer.abstracttool.toolkits.tool00008.Tool00008Info
import com.abstractplayer.abstracttool.toolkits.tool00008.Tool00008MainActivity
import com.abstractplayer.abstracttool.toolkits.tool00009.Tool00009Info
import com.abstractplayer.abstracttool.toolkits.tool00009.Tool00009MainActivity
import com.abstractplayer.abstracttool.toolkits.tool00010.Tool00010Info
import com.abstractplayer.abstracttool.toolkits.tool00010.Tool00010MainActivity


class ToolList {
    companion object{
        val toolList: ArrayList<ToolEntity> = ArrayList()

        init {
            //各位相加
            toolList.add(ToolEntity(Tool00001Info, Tool00001MainActivity::class.java))
            //解数独
            toolList.add(ToolEntity(Tool00002Info, Tool00002MainActivity::class.java))
            //test
            toolList.add(ToolEntity(Tool00003Info, Tool00003MainActivity::class.java))
            //两数相加
            toolList.add(ToolEntity(Tool00004Info, Tool00004MainActivity::class.java))
            //双色球序列生成
            toolList.add(ToolEntity(Tool00005Info, Tool00005MainActivity::class.java))
            //扫码二维码
            toolList.add(ToolEntity(Tool00006Info, Tool00006MainActivity::class.java))
            //生成二维码
            toolList.add(ToolEntity(Tool00007Info, Tool00007MainActivity::class.java))
            //字符串编辑距离
            toolList.add(ToolEntity(Tool00008Info, Tool00008MainActivity::class.java))
            //调色器
            toolList.add(ToolEntity(Tool00009Info, Tool00009MainActivity::class.java))
            //两数相乘
            toolList.add(ToolEntity(Tool00010Info, Tool00010MainActivity::class.java))
        }

    }
}