package com.example.hunachi.githunaclient.presentation.fragment.event

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hunachi.githunaclient.BR
import com.example.hunachi.githunaclient.R

/**
 * Created by hunachi on 2018/02/05.
 */
class FollowerEventAdapter(
        private val list: MutableList<FollowerEvent>,
        private val callback: (FollowerEvent) -> Unit
) : RecyclerView.Adapter<FollowerEventAdapter.ViewHolder>() {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_event, parent, false)
        return ViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            binding?.setVariable(BR.viewModel, list[position])
            itemView.setOnClickListener {
                callback(list[position])
            }
        }
    }
    
    override fun getItemCount() = list.size
    
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding: ViewDataBinding? = DataBindingUtil.bind(view)
    }
    
}