package com.example.hunachi.githunaclient.presentation.fragment.event

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hunachi.githunaclient.databinding.FragmentFollowerEventBinding
import com.example.hunachi.githunaclient.presentation.base.BaseFragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder

/**
 * Created by hunachi on 2018/02/04.
 */
class FollowerEventFragment : BaseFragment() {
    
    lateinit var binding: FragmentFollowerEventBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFollowerEventBinding.inflate(inflater, container!!, false)
        return binding.root
    }
    
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpRecycler()
    }
    
    private fun setUpRecycler(){
        val groupAdapter =  GroupAdapter<ViewHolder>()
                .apply {
                
                }
    }
    
}