package com.asplayer.nameless.util

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import java.io.File
import java.io.FileOutputStream
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*



/**
 * 需要动态权限
 * Manifest.permission.WRITE_EXTERNAL_STORAGE
 */
object LayoutPhotoUtil {
    fun layoutToPhoto(view: View, context: Context){
        saveBitmap(newBitmap(view), context)
    }

    private fun newBitmap(view: View): Bitmap {
        val width = view.width
        val height = view.height

        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        return bitmap
    }

    @SuppressLint("SimpleDateFormat")
    private fun saveBitmap(bitmap: Bitmap, context: Context){
        val format: DateFormat = SimpleDateFormat("yyyyMMddHHmmss")
        val bitName = format.format(Date()).toString() + ".JPEG"
        val file: File
        val fileName: String = if (Build.BRAND == "Xiaomi"){
            Environment.getExternalStorageDirectory().path + "/DCIM/Camera/" + bitName
        }
        else{
            Environment.getExternalStorageDirectory().path + "/DCIM/" + bitName
        }
        file = File(fileName)
        if(file.exists()){
            file.delete()
        }
        val out : FileOutputStream
        try {
            out = FileOutputStream(file)
            // 格式为 JPEG，照相机拍出的图片为JPEG格式的，PNG格式的不能显示在相册中
            if(bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)){
                out.flush()
                out.close()
                MediaStore.Images.Media.insertImage(context.contentResolver, file.absolutePath, bitName, null)
            }
            Toast.makeText(context, "保存成功", Toast.LENGTH_SHORT).show()
        }
        catch (e: Exception){
            Toast.makeText(context, "保存失败", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
        // 发送广播，通知刷新图库的显示
        context.sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://$fileName")))


    }
}