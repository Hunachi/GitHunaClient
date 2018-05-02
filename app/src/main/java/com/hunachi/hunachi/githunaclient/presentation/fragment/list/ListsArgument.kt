package com.hunachi.hunachi.githunaclient.presentation.fragment.list

import java.io.Serializable

/**
 * Created by hunachi on 2018/02/24.
 */
data class ListsArgument(
        val userName: String,
        val listsType: ListType
): Serializable