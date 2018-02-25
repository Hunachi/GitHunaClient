package com.example.hunachi.githunaclient.presentation.fragment.list

import com.example.hunachi.githunaclient.presentation.fragment.viewpager.ListType
import java.io.Serializable

/**
 * Created by hunachi on 2018/02/24.
 */
data class ListsArgument(
        val userName: String,
        val listsType: ListType
): Serializable