package com.example.hunachi.githunaclient.presentation.fragment.event

import com.example.hunachi.githunaclient.R
import com.example.hunachi.githunaclient.databinding.ItemEventBinding
import com.xwray.groupie.databinding.BindableItem

/**
 * Created by hunachi on 2018/02/05.
 */
data class FollowerEventItem (
        val followerEvent: FollowerEvent
) : BindableItem<ItemEventBinding>(followerEvent.id.toLong()) {
    
    override fun bind(viewBinding: ItemEventBinding, position: Int) {
        viewBinding.viewModel = followerEvent
    }
    override fun getLayout(): Int = R.layout.item_event
}