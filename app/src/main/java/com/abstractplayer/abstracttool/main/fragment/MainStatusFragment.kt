package com.abstractplayer.abstracttool.main.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abstractplayer.abstracttool.databinding.FragmentMainStatusBinding
import com.abstractplayer.abstracttool.main.status.dreamland.DreamlandKey
import com.abstractplayer.abstracttool.main.status.dreamland.DreamlandMainActivity
import com.tencent.mmkv.MMKV

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainStatusFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

/**
 * new Fragment的时候不会走任何生命周期，例如加载ViewPager时
 * 当切换到该页面，生命周期为
 * onAttach -> onCreate -> onCreateView -> onActivityCreate -> onStart -> onResume
 * 熄屏了，生命周期为
 * onPause -> onStop
 * 重新开屏，生命周期为
 * onStart -> onResume
 * 在ViewPager切换，生命周期在onResume和onPause来回
 * 退出当前Activity，生命周期为
 * onPause -> onStart -> onDestroyView -> onDestroy -> onDetach
 * onStop表示完全不可见，onPause表示失去焦点、可能可见
 * onStart表示可见了，onResume表示有焦点、完全可见
 */
class MainStatusFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val binding by lazy { FragmentMainStatusBinding.inflate(layoutInflater) }

    private val charChinese by lazy { MMKV.defaultMMKV().decodeString(DreamlandKey.KEY_CHINESE_CHAR, "荣") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        Log.d(TAG, "onCreate: ")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.d(TAG, "onCreateView: ")
        // Inflate the layout for this fragment
        return binding.root
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "onAttach: ")
    }
    

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG, "onActivityCreated: ")

        binding.tvMainStatusChineseChar.text = charChinese
        binding.tvMainStatusChineseChar.setOnLongClickListener {
            val intent = Intent(activity, DreamlandMainActivity::class.java)
            startActivityForResult(intent, CODE_REQUEST_DREAMLAND)
            true
        }
    }


    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }


    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
    }


    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")
    }
    

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
    }


    override fun onDetach() {
        super.onDetach()
        Log.d(TAG, "onDetach: ")
    }


    override fun onDestroyOptionsMenu() {
        super.onDestroyOptionsMenu()
        Log.d(TAG, "onDestroyOptionsMenu: ")
    }


    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView: ")
    }


    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }
    
    

    companion object {
        const val TAG = "MainStatusFragment"
        const val CODE_REQUEST_DREAMLAND = 1
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainStatusFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                MainStatusFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}