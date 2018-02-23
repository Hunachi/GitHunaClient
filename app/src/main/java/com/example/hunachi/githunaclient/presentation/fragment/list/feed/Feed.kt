package com.example.hunachi.githunaclient.presentation.fragment.list.feed

/**
 * Created by hunachi on 2018/02/05.
 */
data class Feed(
        var id: Long = 0,
        var actor: String = "",
        var avatarUrl: String? = "",
        var repositoryUrl: String = "",
        var action: String = "coding!!",
        var repositoryName: String = ""
)