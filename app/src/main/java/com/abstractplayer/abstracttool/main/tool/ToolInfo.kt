package com.abstractplayer.abstracttool.main.tool


interface ToolInfo {
    //模块id
    fun getId(): String{
        return "id_tool_none"
    }

    //模块名称
    fun getName(): String{
        return "未知工具"
    }

    //模块最后更新时间
    fun getTime(): String{
        return "未知时间"
    }

    //模块简介
    fun getIntroduction() : String{
        return "未知简介"
    }

    //模块扩展描述键值对
    fun getInfoMaps(): Map<String, String>{
        return HashMap()
    }
}