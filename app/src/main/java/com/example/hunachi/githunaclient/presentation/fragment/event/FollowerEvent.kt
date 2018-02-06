package com.example.hunachi.githunaclient.presentation.fragment.event

import com.example.hunachi.githunaclient.data.api.responce.Repo

/**
 * Created by hunachi on 2018/02/05.
 */
//TODO I have to make converter from Event to FollowerEvent
data class FollowerEvent(
        var id: Int = 0,
        var actor: String = "hunachi",
        var avatarUrl: String = "https://avatars2.githubusercontent.com/u/16878520?s=400&u=a1a1322309104d47f4345c3170a7c5712e09f076&v=4",
        var action: String = "coding!!",
        var repositoryName: String = "gitHunaClient"
)