package com.example.hunachi.githunaclient.data.api.oauth

import android.content.Intent
import android.net.Uri
import com.example.hunachi.githunaclient.util.Key
import com.example.hunachi.githunaclient.util.Scopes

/**
 * Created by hunachi on 2018/01/29.
 */
class OauthAdapter(private val scopes: Scopes){
    
    companion object {
        const val baseUrl = "https://github.com/login/oauth/authorize"
        const val clientId: String = Key.clientId
        const val state: String = Key.state
    }
    
    private val url = baseUrl + "?" +
            "client_id=" + clientId +
            "&scopes=" + scopes.mapIndexed { index, s -> if(index != scopes.lastIndex)s + "+" else s } +
            "&state=" + state
    
    val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(url)
    )
}