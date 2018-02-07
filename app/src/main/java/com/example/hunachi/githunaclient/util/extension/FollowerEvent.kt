package com.example.hunachi.githunaclient.util.extension

import android.util.Log
import com.example.hunachi.githunaclient.data.api.responce.Event
import com.example.hunachi.githunaclient.presentation.fragment.event.FollowerEvent

/**
 * Created by hunachi on 2018/02/07.
 */
fun Event.convertToFollowerEvent() =
        FollowerEvent().apply {
            id = this@convertToFollowerEvent.id.toLong()
            actor = this@convertToFollowerEvent.actor.userName + "  made "
            avatarUrl = this@convertToFollowerEvent.actor.avatarUrl
            action = type.convertNiceText()
            repositoryName = "at " + when {
                repo?.fullName != null -> repo.fullName
                repo?.name != null     -> repo.name
                else                   -> "NULL"
            }
        }