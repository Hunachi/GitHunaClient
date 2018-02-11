package com.example.hunachi.githunaclient.presentation.fragment.event

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hunachi.githunaclient.databinding.FragmentFollowerEventBinding
import com.example.hunachi.githunaclient.presentation.base.BaseFragment
import com.github.salomonbrys.kodein.instance
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
        followerEventAdapter = FollowerEventAdapter(followerEventList, viewModel.callback)
        binding.apply {
            viewModel = this@FollowerEventFragment.viewModel
            setLifecycleOwner(this@FollowerEventFragment)
            list.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = followerEventAdapter
            }
        }
        viewModel.apply {
            event.observe(this@FollowerEventFragment, Observer { event ->
                followerEventList.apply {
                    //O(N^2)is not good. 10^4 unit is a limit.
                    if (find { it == event } == null) {
                        event?.let { add(0, it) }
                        followerEventAdapter.notifyItemInserted(0)
                    }
                }
            })
            refreshing.observe(this@FollowerEventFragment, Observer {
                swiperefresh.isRefreshing = it ?: false
            })
        }
    }
    
    companion object {
        fun newInstance(): FollowerEventFragment {
            val fragment = FollowerEventFragment()
            //todo user
            return fragment
        }
    }
    
}