package com.abstractplayer.abstracttool.main.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import com.abstractplayer.abstracttool.databinding.FragmentMainPersonBinding
import com.abstractplayer.abstracttool.main.person.MainPersonLoginActivity
import com.abstractplayer.abstracttool.main.person.MainPersonSettingActivity
import com.abstractplayer.abstracttool.nameless.NamelessMainActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainPersonFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainPersonFragment : Fragment() {
    private val binding by lazy {  FragmentMainPersonBinding.inflate(layoutInflater) }

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }


    @SuppressLint("ClickableViewAccessibility")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setListener()
    }

    override fun onDestroy() {
        super.onDestroy()

    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setListener(){
        binding.mainPersonContainAccount.setOnClickListener {
            val intent = Intent(activity, MainPersonLoginActivity::class.java)
            startActivityForResult(intent, CODE_REQUEST_LOGIN)
        }

        binding.mainPersonBtnSetting.container.setOnClickListener {
            val intent = Intent(activity, MainPersonSettingActivity::class.java)
            startActivityForResult(intent, CODE_REQUEST_SETTING)
        }

        binding.btnNamelessStartup.container.setOnLongClickListener {
            context?.let { it1 -> MaterialAlertDialogBuilder(it1)
                .setTitle("         ")
                .setMessage("                                                           ")
                .setPositiveButton("         "){dialog, which ->
                    val intent = Intent(activity, NamelessMainActivity::class.java)
                    startActivityForResult(intent, CODE_REQUEST_NAMELESS)
                }
                .setNegativeButton("  "){dialog, which ->

                }
                .show() }
            true
        }
    }


    companion object {
        const val TAG = "MainPersonFragment"
        const val CODE_REQUEST_LOGIN = 1
        const val CODE_REQUEST_SETTING = 2
        const val CODE_REQUEST_NAMELESS = 3


        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainPersonFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainPersonFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}