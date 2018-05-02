package com.hunachi.hunachi.githunaclient.presentation.fragment.list.repository

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hunachi.hunachi.githunaclient.BR
import com.hunachi.hunachi.githunaclient.R
import com.hunachi.hunachi.githunaclient.data.api.responce.Repository
import com.hunachi.hunachi.githunaclient.databinding.ItemRepositoryBinding
import com.hunachi.hunachi.githunaclient.presentation.fragment.list.BaseItem
import com.hunachi.hunachi.githunaclient.util.ItemCallback

/**
 * Created by hunachi on 2018/02/24.
 */
class RepositoryAdapter(
        private val list: MutableList<BaseItem>,
        private val callback: ItemCallback
) : RecyclerView.Adapter<RepositoryAdapter.ViewHolder>() {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_repository, parent, false)
        return ViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding?.apply {
            setVariable(BR.item, list[position])
            itemBodyLayout.setOnClickListener {
                callback(list[position] as Repository)
            }
        }
    }
    
    override fun getItemCount() = list.size
    
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding: ItemRepositoryBinding? = DataBindingUtil.bind(view)
    }
}