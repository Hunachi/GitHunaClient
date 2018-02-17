package com.example.hunachi.githunaclient.kodein

import com.example.hunachi.githunaclient.presentation.fragment.ViewpagerViewModel
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton

/**
 * Created by hunachi on 2018/02/17.
 */
val viewPagerViewModelModule = Kodein.Module{
    bind<ViewpagerViewModel>() with singleton {
        ViewpagerViewModel(
            application = instance()
        )
    }
}