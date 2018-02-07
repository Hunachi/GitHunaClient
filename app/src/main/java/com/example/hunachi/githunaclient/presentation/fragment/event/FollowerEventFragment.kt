package com.example.hunachi.githunaclient.presentation.fragment.event

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.hunachi.githunaclient.data.api.responce.Repo
import com.example.hunachi.githunaclient.databinding.FragmentFollowerEventBinding
import com.example.hunachi.githunaclient.presentation.base.BaseFragment
import com.example.hunachi.githunaclient.util.EventCallback
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.with

/**
 * Created by hunachi on 2018/02/04.
 */
class FollowerEventFragment : BaseFragment() {
    
    //TODO list itemが崩壊．
    
    private lateinit var binding: FragmentFollowerEventBinding
    private val followerEventList = mutableListOf<FollowerEvent>()
    private lateinit var followerEventAdapter: FollowerEventAdapter
    private val viewModel: FollowerEventViewModel by /*with(this as BaseFragment).*/instance()
    
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
    
    private fun setUpRecycler() {
        setViewModel(viewModel)
        followerEventAdapter = FollowerEventAdapter(followerEventList, viewModel.callback)
        binding.list.apply {
            layoutManager = LinearLayoutManager(binding.list.context)
            adapter = followerEventAdapter
        }
        viewModel.eventList.observe(this, Observer {
            it?.let {
                followerEventList.add(it)
                followerEventAdapter.notifyItemInserted(0)
            }
        })
        /*viewModel.list.observeForever {
            val events = followerEventList.size
            *//*(0 .. it.size).forEach {
                followerEventList
            }*//*
            followerEventAdapter.notifyItemRangeInserted(size, it.size)
        }*/
    }
    
    //todo
    private val callback: EventCallback = { Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show() }
    
    companion object {
        fun newInstance(): FollowerEventFragment {
            return FollowerEventFragment()
        }
    }
    
}