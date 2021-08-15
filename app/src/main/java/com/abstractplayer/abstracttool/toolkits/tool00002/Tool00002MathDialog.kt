package com.abstractplayer.abstracttool.toolkits.tool00002

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.abstractplayer.abstracttool.databinding.Tool00002DialogMathBinding

/**
 ** Created by 79260 at 2021/8/3 12:02.
 */
class Tool00002MathDialog(private val mathView: Tool00002MathView, val selectIndexX: Int, val selectIndexY: Int)
    : DialogFragment(){
    companion object{
        const val TAG = "Tool00002MathDialog"
    }

    private lateinit var binding: Tool00002DialogMathBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = Tool00002DialogMathBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setListener()
    }


    private fun setListener(){
        binding.tool00002DialogMath1.setOnClickListener {
            mathView.setDigitsList(selectIndexX, selectIndexY, 1)
            dismiss()
        }

        binding.tool00002DialogMath2.setOnClickListener {
            mathView.setDigitsList(selectIndexX, selectIndexY, 2)
            dismiss()
        }

        binding.tool00002DialogMath3.setOnClickListener {
            mathView.setDigitsList(selectIndexX, selectIndexY, 3)
            dismiss()
        }

        binding.tool00002DialogMath4.setOnClickListener {
            mathView.setDigitsList(selectIndexX, selectIndexY, 4)
            dismiss()
        }

        binding.tool00002DialogMath5.setOnClickListener {
            mathView.setDigitsList(selectIndexX, selectIndexY, 5)
            dismiss()
        }

        binding.tool00002DialogMath6.setOnClickListener {
            mathView.setDigitsList(selectIndexX, selectIndexY, 6)
            dismiss()
        }
        binding.tool00002DialogMath7.setOnClickListener {
            mathView.setDigitsList(selectIndexX, selectIndexY, 7)
            dismiss()
        }

        binding.tool00002DialogMath8.setOnClickListener {
            mathView.setDigitsList(selectIndexX, selectIndexY, 8)
            dismiss()
        }

        binding.tool00002DialogMath9.setOnClickListener {
            mathView.setDigitsList(selectIndexX, selectIndexY, 9)
            dismiss()
        }

        binding.tool00002DialogMath0.setOnClickListener {
            mathView.setDigitsList(selectIndexX, selectIndexY, 0)
            dismiss()
        }

        binding.tool00002DialogMathCancel.setOnClickListener {
            dismiss()
        }

        binding.tool00002DialogMathClear.setOnClickListener {
            mathView.clearDigitsList()
            dismiss()
        }

        binding.tool00002DialogMathInfo.setOnClickListener {
            binding.tool00002DialogMathInfo.text = "当前坐标：（${selectIndexX + 1}， ${selectIndexY + 1}）"
            dismiss()
        }
    }

}