package com.example.hunachi.githunaclient.presentation.fragment.event

import android.view.View
import android.widget.Toast
import com.example.hunachi.githunaclient.R
import com.example.hunachi.githunaclient.databinding.ItemEventBinding
import com.example.hunachi.githunaclient.presentation.base.BaseFragment
import com.xwray.groupie.databinding.BindableItem
import com.xwray.groupie.databinding.ViewHolder

/**
 * Created by hunachi on 2018/02/05.
 */
data class FollowerEventItem (
        private val followerEvent: FollowerEvent
) : BindableItem<ItemEventBinding>() {
    
    override fun bind(viewBinding: ItemEventBinding, position: Int) {
        viewBinding.viewModel = followerEvent
    }
    
    override fun getLayout(): Int = R.layout.item_event
}