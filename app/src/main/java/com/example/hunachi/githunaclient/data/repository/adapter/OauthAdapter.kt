package com.example.hunachi.githunaclient.data.repository.adapter

import android.content.Intent
import android.net.Uri
import com.example.hunachi.githunaclient.model.Key
import com.example.hunachi.githunaclient.util.Scopes

/**
 * Created by hunachi on 2018/01/29.
 */
object OauthAdapter{
    
    /*if you want scope to change, please change item at mutableList.*/
    val scopes = mutableListOf("repo")
    val baseUrl = "https://github.com/login/oauth/authorize"
    val clientId: String = Key.clientId
    val state: String = Key.state
    
    private val url = baseUrl + "?" +
            "client_id=" + clientId +
            "&scopes=" + scopes.mapIndexed { index, s -> if(index != scopes.lastIndex)s + "+" else s } +
            "&state=" + state
    
    val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(url)
    )
}