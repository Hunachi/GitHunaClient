package com.example.hunachi.githunaclient.presentation.fragment.event

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.hunachi.githunaclient.data.api.responce.Repo
import com.example.hunachi.githunaclient.databinding.FragmentFollowerEventBinding
import com.example.hunachi.githunaclient.presentation.base.BaseFragment

/**
 * Created by hunachi on 2018/02/04.
 */
class FollowerEventFragment : BaseFragment() {
    
    //TODO make add fragment 何回もセットされてしまって勿体無い．
    //TODO list itemが崩壊．
    
    private lateinit var binding: FragmentFollowerEventBinding
    private val followerEventList = mutableListOf<FollowerEvent>()
    private lateinit var followerEventAdapter: FollowerEventAdapter
    
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
        followerEventAdapter = FollowerEventAdapter(followerEventList, callback)
        binding.list.apply {
            layoutManager = LinearLayoutManager(binding.list.context)
            adapter = followerEventAdapter
        }
    }
    
    private val callback: (FollowerEvent) -> Unit = {
        Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
    }
    
    companion object {
        fun newInstance(): FollowerEventFragment{
            return FollowerEventFragment()
        }
    }
    
}