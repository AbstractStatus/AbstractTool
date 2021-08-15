package com.abstractplayer.abstracttool.toolkits.tool00001

import com.abstractplayer.abstracttool.main.tool.ToolInfo

class Tool00001Info {
    companion object : ToolInfo{
        override fun getId(): String {
            return "id_tool_00001"
        }

        override fun getName(): String {
            return "各位相加"
        }
    }
}