package com.abstractplayer.abstracttool.common.utils

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.*


/**
 ** Created by 79260 at 2021/8/14 21:47.
 */
class PhotoUtil {

    companion object{
        /**保存bitmap */
        fun saveBitmap(bitmap: Bitmap, context: Context) {
            val extStorageDirectory: String =
                Environment.getExternalStorageDirectory().toString()
            var outStream: OutputStream? = null
            val filename: String //声明文件名
            //以保存时间为文件名
            val date = Date(System.currentTimeMillis())
            val sdf = SimpleDateFormat("yyyyMMddHHmmss")
            filename = sdf.format(date)
            val file = File(extStorageDirectory, "$filename.JPEG") //创建文件，第一个参数为路径，第二个参数为文件名
            try {
                outStream = FileOutputStream(file) //创建输入流
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream)
                outStream.close()
                //这三行可以实现相册更新
                val intent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                val uri = Uri.fromFile(file);
                intent.data = uri;
                context.sendBroadcast(intent);

                 //这个广播的目的就是更新图库，发了这个广播进入相册就可以找到你保存的图片了！
                Toast.makeText(context, "保存成功", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(context, "保存失败：错误码: $e", Toast.LENGTH_SHORT).show()
            }
        }
    }
}