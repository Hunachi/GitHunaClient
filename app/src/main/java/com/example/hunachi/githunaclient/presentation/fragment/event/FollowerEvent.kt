package com.example.hunachi.githunaclient.presentation.fragment.event

import com.example.hunachi.githunaclient.data.api.responce.Repo

/**
 * Created by hunachi on 2018/02/05.
 */
//TODO I have to make converter from Event to FollowerEvent
data class FollowerEvent(
        val id: Int = 0,
        val actor: String = "hunachi",
        val avatarUrl: String = "po",
        val action: String = "swimming",
        val repository: Repo = Repo(0,"name","fullname",null),
        val repositoryOwnerName: String = "hunachi",
        val language: String = "kotlin"
)