package com.hunachi.hunachi.githunaclient.presentation.main

import com.hunachi.hunachi.githunaclient.R
import java.io.Serializable

/**
 * Created by hunachi on 2018/02/26.
 */
enum class FragmentFrag(val id: Int) : Serializable {
    PROFILE(R.id.action_profile), TL(R.id.action_feed), LISTS(R.id.action_lists);
    
    companion object {
        fun frag(id: Int): FragmentFrag =
                when (id) {
                    R.id.action_profile -> PROFILE
                    R.id.action_feed    -> TL
                    R.id.action_lists   -> LISTS
                    else                -> TL
                }
    }
}

const val FRAGMENT_FRAG_NAME = "frag"