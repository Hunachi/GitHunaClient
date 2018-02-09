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
import kotlinx.android.synthetic.main.fragment_follower_event.*

/**
 * Created by hunachi on 2018/02/04.
 */
class FollowerEventFragment : BaseFragment() {
    
    private lateinit var binding: FragmentFollowerEventBinding
    private val followerEventList = mutableListOf<FollowerEvent>()
    private lateinit var followerEventAdapter: FollowerEventAdapter
    private val viewModel: FollowerEventViewModel by instance()
    
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
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)
        followerEventAdapter = FollowerEventAdapter(followerEventList, viewModel.callback)
        binding.list.apply {
            layoutManager = LinearLayoutManager(binding.list.context)
            adapter = followerEventAdapter
        }
        viewModel.apply {
            eventList.observe(this@FollowerEventFragment, Observer { event ->
                followerEventList.apply {
                    //O(N^2)になるのでよくなさすぎる．10^4個が限界．
                    if (find { it == event } == null) {
                        event?.let { add(it) }
                        followerEventAdapter.notifyItemInserted(0)
                    }
                }
            })
            refreshing.observe(this@FollowerEventFragment, Observer {
                swiperefresh.isRefreshing = it?: false
            })
        }
    }
    
    companion object {
        fun newInstance(): FollowerEventFragment {
            return FollowerEventFragment()
        }
    }
    
}