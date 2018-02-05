package com.example.hunachi.githunaclient.presentation.fragment.event

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.hunachi.githunaclient.databinding.FragmentFollowerEventBinding
import com.example.hunachi.githunaclient.presentation.base.BaseFragment
import com.xwray.groupie.Group
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
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
    
    private fun setUpRecycler() {
        val groupAdapter = GroupAdapter<ViewHolder>().apply {
            setOnItemClickListener { item, view ->
                Toast.makeText(activity, (item as FollowerEventItem).toString(), Toast.LENGTH_SHORT).show()
            }
        }
        binding.recycler.apply {
            adapter = groupAdapter
            layoutManager = LinearLayoutManager(binding.recycler.context) as RecyclerView.LayoutManager?
        }
        groupAdapter.add(FollowerEventItem(FollowerEvent()))
        groupAdapter.notifyItemInserted(0)
    }
    
    companion object {
        fun newInstance(): FollowerEventFragment{
            val fragment = FollowerEventFragment()
            //todo user
            return fragment
        }
    }
    
}