package com.abstractplayer.abstracttool.toolkits.tool00002

import com.abstractplayer.abstracttool.main.tool.ToolInfo

class Tool00002Info {
    companion object : ToolInfo{
        override fun getId(): String {
            return "id_tool_00002"
        }

        override fun getName(): String {
            return "解数独"
        }
    }
}