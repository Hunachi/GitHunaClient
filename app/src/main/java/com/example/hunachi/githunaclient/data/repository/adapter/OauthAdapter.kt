package com.example.hunachi.githunaclient.data.repository.adapter

import android.content.Intent
import android.net.Uri
import com.example.hunachi.githunaclient.util.Key

/**
 * Created by hunachi on 2018/01/29.
 */
object OauthAdapter {
    
    private const val scopes = "repo,read:user"
    private const val baseUrl = "https://github.com/login/oauth/authorize"
    private const val clientId: String = Key.CLIENT_ID
    private const val state: String = Key.state
    
    private const val url = baseUrl + "?" +
            "client_id=" + clientId +
            "&scope=" + scopes +
            "&state=" + state
    
    val intent = Intent(
        Intent.ACTION_VIEW,
        Uri.parse(url)
    )
}