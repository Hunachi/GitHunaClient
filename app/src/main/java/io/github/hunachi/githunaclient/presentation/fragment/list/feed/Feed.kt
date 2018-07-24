package io.github.hunachi.githunaclient.presentation.fragment.list.feed

import io.github.hunachi.githunaclient.presentation.fragment.list.BaseItem

/**
 * Created by hunachi on 2018/02/05.
 */
data class Feed(
        var id: Long = 0,
        var actorUserName: String = "",
        var actorName: String? = "",
        var avatarUrl: String? = "",
        var repositoryUrl: String = "",
        var action: String = "coding!!",
        var repositoryName: String = ""
): BaseItem
