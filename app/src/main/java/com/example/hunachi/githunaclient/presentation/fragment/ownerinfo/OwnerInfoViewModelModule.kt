package com.example.hunachi.githunaclient.presentation.fragment.ownerinfo

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.singleton

/**
 * Created by hunachi on 2018/02/28.
 */
//TODO まとめる
val ownerInfoViewModelModule = Kodein.Module{
    bind<OwnerInfoViewModel>() with singleton {
        OwnerInfoViewModel()
    }
}