package com.abstractplayer.abstracttool.toolkits.tool00006

import com.abstractplayer.abstracttool.common.key.HashMapKey
import com.abstractplayer.abstracttool.main.tool.ToolInfo

/**
 ** Created by 79260 at 2021/8/14 15:50.
 */
class Tool00006Info {
    companion object: ToolInfo{
        override fun getId(): String {
            return "id_tool_00006"
        }

        override fun getName(): String {
            return "扫码二维码"
        }

        override fun getTime(): String {
            return "2021-08-14"
        }

        override fun getInfoMaps(): Map<String, String> {
            return HashMap<String, String>().apply{
                put(HashMapKey.TOOL_INFO_MAP_KEY_THIRD_PART_LIBRARY,
                    "1 - 华为统一扫码服务\n" +
                            "2 - PermissionX")

                put(HashMapKey.TOOL_INFO_MAP_KEY_PERMISSION,
                "1 - 相机权限\n" +
                        "2 - 存储权限")
            }
        }
    }
}