package com.abstractplayer.abstracttool.toolkits.tool00006

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.abstractplayer.abstracttool.common.base.BaseActivity
import com.abstractplayer.abstracttool.common.utils.SysServiceTool
import com.abstractplayer.abstracttool.databinding.ActivityTool00006MainBinding
import com.huawei.hms.hmsscankit.ScanUtil
import com.huawei.hms.ml.scan.HmsScan
import com.huawei.hms.ml.scan.HmsScanAnalyzerOptions
import com.permissionx.guolindev.PermissionX
import com.permissionx.guolindev.callback.ExplainReasonCallback
import com.permissionx.guolindev.callback.ForwardToSettingsCallback
import com.permissionx.guolindev.callback.RequestCallback



class Tool00006MainActivity : BaseActivity() {
    companion object{
        const val TAG = "Tool00006MainActivity"
        const val REQUEST_CODE_SCAN = 1
    }

    private lateinit var binding: ActivityTool00006MainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTool00006MainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListener()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode != RESULT_OK || data == null) {
            return
        }
        //从onActivityResult返回data中，用ScanUtil.RESULT作为key值取到HmsScan返回值
        if (requestCode == REQUEST_CODE_SCAN) {
            val obj: Any? = data?.getParcelableExtra(ScanUtil.RESULT)
            if (obj is HmsScan?) {
                if (!TextUtils.isEmpty(obj?.getOriginalValue())) {
                    binding.tool00006ScanText.visibility = View.VISIBLE
                    binding.tool00006BtnCopy.visibility = View.VISIBLE
                    binding.tool00006ScanText.text = obj?.getOriginalValue()
                }
                return
            }

        }
    }


    private fun setListener(){
        binding.tool00006BtnScan.container.setOnClickListener {
            requestPermission()
        }

        binding.tool00006BtnCopy.container.setOnClickListener {
            SysServiceTool.copyContentToClipboard(
                binding.tool00006ScanText.text.toString(),
                this
            )
        }
    }


    private fun requestPermission(){
        PermissionX.init(this)
            .permissions(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)
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
                    scanCode()
                } else {
                    Toast.makeText(this, "这些权限被禁止: $deniedList", Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun scanCode(){
        ScanUtil.startScan(this, REQUEST_CODE_SCAN, HmsScanAnalyzerOptions.Creator().setHmsScanTypes(HmsScan.ALL_SCAN_TYPE).create())
    }
}