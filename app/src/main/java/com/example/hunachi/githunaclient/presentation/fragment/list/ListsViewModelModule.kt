package com.example.hunachi.githunaclient.presentation.fragment.list

import com.example.hunachi.githunaclient.presentation.application.MyApplication
import com.example.hunachi.githunaclient.util.ListType
import com.github.salomonbrys.kodein.*

/**
 * Created by hunachi on 2018/02/07.
 */
val eventViewModelModule = Kodein.Module {
    bind<ListsViewModel>() with multiton { listsArgument: ListsArgument ->
        ListsViewModel(
            githubApiRepository = with((instance() as MyApplication)).instance(),
            listsArgument = listsArgument,
            schedulers = instance()
        )
    }
}