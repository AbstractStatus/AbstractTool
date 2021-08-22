package com.abstractplayer.abstracttool.main.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.abstractplayer.abstracttool.R
import com.abstractplayer.abstracttool.databinding.FragmentMainToolBinding
import com.abstractplayer.abstracttool.main.tool.ToolList
import com.abstractplayer.abstracttool.main.tool.ToolListAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainToolFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainToolFragment : Fragment() {
    private lateinit var toolListAdapter: ToolListAdapter
    private lateinit var binding: FragmentMainToolBinding

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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainToolBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//                lifecycle.addObserver(binding.funcView)
        toolListAdapter = ToolListAdapter(requireActivity())
        binding.mainToolRecycleView.layoutManager = LinearLayoutManager(context)
        binding.mainToolRecycleView.adapter = toolListAdapter
        toolListAdapter.notifyDataSetChanged()

        binding.mainToolSearchBar.setOnSearchTextChange {
            for(tool in ToolList.toolList){
                if(tool.info.getName().contains(it)){
                    tool.visible = View.VISIBLE
                }
                else{
                    tool.visible = View.GONE
                }
            }
            toolListAdapter.notifyDataSetChanged()
        }

        binding.mainToolSearchBar.setOnRightButtonClick {

        }
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG, "onDetach: ")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView: ")
    }

    //应用退出后，静态变量并没有销毁，需要在重新设置可见性，避免退出后再进来可见性异常
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
        for(tool in ToolList.toolList){
            tool.visible = View.VISIBLE
        }
    }


    companion object {
        const val TAG = "MainToolFragment"
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainToolFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                MainToolFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}