package com.example.hunachi.githunaclient.kodein

import android.support.v7.app.AppCompatActivity
import com.example.hunachi.githunaclient.presentation.MyApplication
import com.example.hunachi.githunaclient.presentation.fragment.list.ListsArgument
import com.example.hunachi.githunaclient.presentation.fragment.list.ListsViewModel
import com.example.hunachi.githunaclient.presentation.fragment.ownerinfo.OwnerInfoViewModel
import com.example.hunachi.githunaclient.presentation.fragment.userinfo.UserInfoViewModel
import com.example.hunachi.githunaclient.presentation.login.LoginGithubViewModel
import com.example.hunachi.githunaclient.presentation.main.MainViewModel
import com.github.salomonbrys.kodein.*
import com.github.salomonbrys.kodein.android.androidActivityScope

/**
 * Created by hunachi on 2018/02/28.
 */

val viewModelModules = Kodein.Module {
    bind<ListsViewModel>() with multiton { listsArgument: ListsArgument ->
        ListsViewModel(
            githubApiRepository = with((instance() as MyApplication)).instance(),
            listsArgument = listsArgument,
            schedulers = instance()
        )
    }
    
    bind<UserInfoViewModel>() with multiton { userName: String ->
        UserInfoViewModel(
            githubApiRepository = with((instance() as MyApplication)).instance(),
            scheduler = instance(),
            userName = userName
        )
    }
    
    bind<LoginGithubViewModel>() with scopedSingleton(androidActivityScope) {
        LoginGithubViewModel(
            navigator = with(it as AppCompatActivity).instance(),
            application = instance()
        )
    }
    
    bind<MainViewModel>() with singleton {
        MainViewModel(
            scheduler = instance(),
            githubApiRepository = with((instance() as MyApplication)).instance()
        )
    }
    
    bind<OwnerInfoViewModel>() with multiton { ownerName: String ->
        OwnerInfoViewModel(
            githubApiRepository = with((instance() as MyApplication)).instance(),
            scheduler = instance(),
            ownerName = ownerName
        )
    }
}