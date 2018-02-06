package com.example.hunachi.githunaclient.presentation.fragment.event

import com.example.hunachi.githunaclient.data.api.responce.Repo

/**
 * Created by hunachi on 2018/02/05.
 */
//TODO I have to make converter from Event to FollowerEvent
data class FollowerEvent(
        var id: Int = 0,
        var actor: String = "hunachi",
        var avatarUrl: String = "https://github.com/hunachi",
        var action: String = "coding!!",
        var repositoryName: String = "gitHunaClient"
)