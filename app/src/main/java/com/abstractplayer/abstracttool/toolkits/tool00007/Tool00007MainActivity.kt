package com.abstractplayer.abstracttool.toolkits.tool00007

import android.Manifest
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.abstractplayer.abstracttool.common.base.BaseActivity
import com.abstractplayer.abstracttool.common.utils.KeyBoardUtil
import com.abstractplayer.abstracttool.common.utils.PhotoUtil
import com.abstractplayer.abstracttool.databinding.ActivityTool00007MainBinding
import com.huawei.hms.hmsscankit.ScanUtil
import com.huawei.hms.hmsscankit.WriterException
import com.huawei.hms.ml.scan.HmsBuildBitmapOption
import com.huawei.hms.ml.scan.HmsScan
import com.permissionx.guolindev.PermissionX
import com.permissionx.guolindev.callback.ExplainReasonCallback
import com.permissionx.guolindev.callback.ForwardToSettingsCallback
import com.permissionx.guolindev.callback.RequestCallback


class Tool00007MainActivity : BaseActivity() {
    private lateinit var qrBitmap: Bitmap

    companion object{
        const val TAG = "Tool00007MainActivity"
    }

    private lateinit var binding: ActivityTool00007MainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTool00007MainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        setListener()
    }


    private fun initView(){
        binding.tool00007CodeImage.visibility = View.GONE
        binding.tool00007CodeSave.visibility = View.GONE
    }


    private fun setListener(){

        binding.tool00007CodeGenerate.container.setOnClickListener {
            PermissionX.init(this)
                .permissions(Manifest.permission.READ_EXTERNAL_STORAGE)
                .onExplainRequestReason(ExplainReasonCallback {
                        scope, deniedList ->
                    scope.showRequestReasonDialog(deniedList, "本工具需要这些权限", "确认", "取消")
                })
                .onForwardToSettings(ForwardToSettingsCallback {
                        scope, deniedList ->
                    scope.showForwardToSettingsDialog(deniedList, "你需要允许这些权限", "确认", "取消")
                })
                .request(RequestCallback {
                        allGranted, grantedList, deniedList ->
                    if (allGranted) {
                        generateQR()
                    } else {
                        Toast.makeText(this, "这些权限被禁止: $deniedList", Toast.LENGTH_SHORT).show()
                    }
                })
        }

        binding.tool00007CodeSave.container.setOnClickListener {
            PermissionX.init(this)
                .permissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .onExplainRequestReason(ExplainReasonCallback {
                        scope, deniedList ->
                    scope.showRequestReasonDialog(deniedList, "本工具需要这些权限", "确认", "取消")
                })
                .onForwardToSettings(ForwardToSettingsCallback {
                        scope, deniedList ->
                    scope.showForwardToSettingsDialog(deniedList, "你需要允许这些权限", "确认", "取消")
                })
                .request(RequestCallback {
                        allGranted, grantedList, deniedList ->
                    if (allGranted) {
                        PhotoUtil.saveBitmap(qrBitmap, this)
                    } else {
                        Toast.makeText(this, "这些权限被禁止: $deniedList", Toast.LENGTH_SHORT).show()
                    }
                })

        }
    }

    private fun generateQR(){
        KeyBoardUtil.hintKeyBoard(this)

        val type = HmsScan.QRCODE_SCAN_TYPE
        val width = 800
        val height = 800

        val options = HmsBuildBitmapOption.Creator().setBitmapBackgroundColor(Color.WHITE).setBitmapColor(
            Color.BLACK).setBitmapMargin(2).create()

        try {
            // 如果未设置HmsBuildBitmapOption对象，生成二维码参数options置null。
            qrBitmap = ScanUtil.buildBitmap(binding.tool00007CodeEdit.text.toString(), type, width, height, options)
            binding.tool00007CodeImage.setImageBitmap(qrBitmap)
            binding.tool00007CodeImage.visibility = View.VISIBLE
            binding.tool00007CodeSave.visibility = View.VISIBLE
        } catch (e: WriterException) {
            Toast.makeText(this, "生成二维码失败", Toast.LENGTH_SHORT).show()
        }
    }
}