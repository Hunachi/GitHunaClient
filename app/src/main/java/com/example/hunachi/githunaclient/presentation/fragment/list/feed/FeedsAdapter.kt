package com.example.hunachi.githunaclient.presentation.fragment.list.feed

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hunachi.githunaclient.BR
import com.example.hunachi.githunaclient.R
import com.example.hunachi.githunaclient.databinding.ItemEventBinding
import com.example.hunachi.githunaclient.util.FeedItemCallback

/**
 * Created by hunachi on 2018/02/05.
 */
class FeedsAdapter(
        private val list: MutableList<Feed>,
        private val iconCallback: FeedItemCallback,
        private val callback: FeedItemCallback
) : RecyclerView.Adapter<FeedsAdapter.ViewHolder>() {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_event, parent, false)
        return ViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding?.apply {
            setVariable(BR.viewModel, list[position])
            actorImageButton.setOnClickListener {
                iconCallback(list[position])
            }
            itemBodyLayout.setOnClickListener {
                callback(list[position])
            }
        }
    }
    
    override fun getItemCount() = list.size
    
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding: ItemEventBinding? = DataBindingUtil.bind(view)
    }
    
}