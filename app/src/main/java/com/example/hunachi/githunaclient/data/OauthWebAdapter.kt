package com.example.hunachi.githunaclient.data

import android.content.Intent
import android.net.Uri

/**
 * Created by hunachi on 2018/01/29.
 */
class OauthWebAdapter(val scopes: MutableList<String> = mutableListOf("repo")){
    
    companion object {
        const val baseUrl = "https://github.com/login/oauth/authorize"
        const val clientId: String = "5bb3a3b68dc0a5ad5f45"
        const val state: String = "git-huna-oauth"
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