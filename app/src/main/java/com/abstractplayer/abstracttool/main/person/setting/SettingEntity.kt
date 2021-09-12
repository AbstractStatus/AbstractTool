package com.abstractplayer.abstracttool.main.person.setting

/**
 ** Created by AbstractStatus at 2021/9/11 22:51.
 */
data class SettingEntity(
        var settingId: Int,
        var settingType: Int,
        var settingName: String,
        var settingData: Any
){
        companion object{
                const val TYPE_BOOLEAN = 0X01
                const val TYPE_INTEGER = 0X02
                const val TYPE_STRING = 0X03
                const val TYPE_VECTOR = 0XFF
        }

        fun checkType(): Boolean {
                return when(settingType){
                        TYPE_BOOLEAN -> settingData is SettingBooleanData
                        TYPE_INTEGER -> settingData is SettingIntegerData
                        TYPE_STRING -> settingData is SettingStringData
                        else -> false
                }
        }
}

data class SettingBooleanData(
        var settingValue: Boolean
){

}

data class SettingIntegerData(
        var settingValue: Int,
        var minValue: Int = 0,
        var maxValue: Int = 100
){

}

data class SettingStringData(
        var settingValue: String
){

}

data class SettingVectorData(
        var vectorValue: Int
){

}