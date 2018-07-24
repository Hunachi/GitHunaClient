package io.github.hunachi.githunaclient.presentation.fragment.list.feed

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.hunachi.githunaclient.BR
import io.github.hunachi.githunaclient.R
import io.github.hunachi.githunaclient.databinding.ItemTimelineBinding
import io.github.hunachi.githunaclient.presentation.fragment.list.BaseItem
import io.github.hunachi.githunaclient.util.ItemCallback

/**
 * Created by hunachi on 2018/02/05.
 */
class TimeLineAdapter(
        private val list: MutableList<BaseItem>,
        private val iconCallback: ItemCallback,
        private val callback: ItemCallback
) : RecyclerView.Adapter<TimeLineAdapter.ViewHolder>() {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_timeline, parent, false)
        return ViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding?.apply {
            setVariable(BR.item, list[position])
            actorImageButton.setOnClickListener {
                iconCallback(list[position] as Feed)
            }
            itemBodyLayout.setOnClickListener {
                callback(list[position] as Feed)
            }
        }
    }
    
    override fun getItemCount() = list.size
    
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding: ItemTimelineBinding? = DataBindingUtil.bind(view)
    }
    
}
