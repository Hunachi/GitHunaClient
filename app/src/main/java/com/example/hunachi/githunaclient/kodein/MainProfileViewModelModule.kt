package com.example.hunachi.githunaclient.kodein

import com.example.hunachi.githunaclient.data.api.responce.User
import com.example.hunachi.githunaclient.presentation.main.profile.MainProfileViewModel
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.factory
import com.github.salomonbrys.kodein.instance

/**
 * Created by hunachi on 2018/02/11.
 */
val mainProfileViewModelModule = Kodein.Module{
    bind<MainProfileViewModel>() with factory { user: User ->
        MainProfileViewModel(
            navigator = instance(),
            application = instance(),
            githubApiRepository = instance(),
            user = user
        )
    }
}