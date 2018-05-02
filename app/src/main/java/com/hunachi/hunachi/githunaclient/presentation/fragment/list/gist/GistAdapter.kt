package com.hunachi.hunachi.githunaclient.presentation.fragment.list.gist

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hunachi.hunachi.githunaclient.BR
import com.hunachi.hunachi.githunaclient.R
import com.hunachi.hunachi.githunaclient.data.api.responce.Gist
import com.hunachi.hunachi.githunaclient.databinding.ItemGistBinding
import com.hunachi.hunachi.githunaclient.presentation.fragment.list.BaseItem
import com.hunachi.hunachi.githunaclient.util.ItemCallback

/**
 * Created by hunachi on 2018/02/25.
 */
class GistAdapter(
    private val list: MutableList<BaseItem>,
    private val callback: ItemCallback
) : RecyclerView.Adapter<GistAdapter.ViewHolder>() {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_gist, parent, false)
        return ViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding?.apply {
            setVariable(BR.item, list[position])
            itemBodyLayout.setOnClickListener {
                callback(list[position] as Gist)
            }
        }
    }
    
    override fun getItemCount() = list.size
    
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding: ItemGistBinding? = DataBindingUtil.bind(view)
    }
}