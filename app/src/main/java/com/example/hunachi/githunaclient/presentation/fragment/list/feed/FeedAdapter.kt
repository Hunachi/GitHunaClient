package com.example.hunachi.githunaclient.presentation.fragment.list.feed

import android.databinding.DataBindingUtil
import android.media.browse.MediaBrowser
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hunachi.githunaclient.BR
import com.example.hunachi.githunaclient.R
import com.example.hunachi.githunaclient.databinding.ItemFeedBinding
import com.example.hunachi.githunaclient.databinding.ItemTimelineBinding
import com.example.hunachi.githunaclient.presentation.fragment.list.BaseItem
import com.example.hunachi.githunaclient.util.ItemCallback

/**
 * Created by hunachi on 2018/02/27.
 */
class FeedAdapter(
        private val list: MutableList<BaseItem>,
        private val callback: ItemCallback
) : RecyclerView.Adapter<FeedAdapter.ViewHolder>() {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_feed, parent, false)
        return ViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding?.apply {
            setVariable(BR.item, list[position])
            itemBodyLayout.setOnClickListener {
                callback(list[position] as Feed)
            }
        }
    }
    
    override fun getItemCount() = list.size
    
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding: ItemFeedBinding? = DataBindingUtil.bind(view)
    }
    
}