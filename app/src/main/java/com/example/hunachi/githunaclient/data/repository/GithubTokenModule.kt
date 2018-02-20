package com.example.hunachi.githunaclient.data.repository

import com.example.hunachi.githunaclient.data.repository.GithubTokenRepository
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.factory
import com.github.salomonbrys.kodein.instance

/**
 * Created by hunachi on 2018/02/04.
 */
val githubTokenModule = Kodein.Module {
    bind<GithubTokenRepository>() with factory { callback: GithubTokenRepository.Callback ->
        GithubTokenRepository(
            scheduler = instance(),
            application = instance(),
            callback = callback
        )
    }
}