package com.example.hunachi.githunaclient.presentation.fragment.event

import com.example.hunachi.githunaclient.data.api.responce.Repo

/**
 * Created by hunachi on 2018/02/05.
 */
//TODO I have to make converter from Event to FollowerEvent
data class FollowerEvent(
        val id: Int,
        val actor: String,
        val avatarUrl: String,
        val action: String,
        val repository: Repo,
        val repositoryOwnerName: String,
        val language: String
)