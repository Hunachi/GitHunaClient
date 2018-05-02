package com.hunachi.hunachi.githunaclient.kodein

import android.content.Context
import com.github.salomonbrys.kodein.*
import com.github.salomonbrys.kodein.android.androidContextScope
import com.hunachi.hunachi.githunaclient.data.repository.GithubApiRepository
import com.hunachi.hunachi.githunaclient.presentation.MainApplication
import com.hunachi.hunachi.githunaclient.presentation.MyApplication
import com.hunachi.hunachi.githunaclient.presentation.dialog.LoadingDialogAdapter
import com.hunachi.hunachi.githunaclient.presentation.dialog.WarningDialogAdapter
import com.hunachi.hunachi.githunaclient.presentation.fragment.list.ListsArgument
import com.hunachi.hunachi.githunaclient.presentation.fragment.list.ListsFragment
import com.hunachi.hunachi.githunaclient.presentation.fragment.ownerinfo.OwnerInfoFragment
import com.hunachi.hunachi.githunaclient.presentation.fragment.userinfo.UserInfoFragment
import com.hunachi.hunachi.githunaclient.presentation.fragment.viewpager.ViewPagerFragment
import com.hunachi.hunachi.githunaclient.presentation.login.LoginGithubActivity
import com.hunachi.hunachi.githunaclient.presentation.main.MainActivity
import com.hunachi.hunachi.githunaclient.presentation.main.profile.MainProfileActivity
import com.hunachi.hunachi.githunaclient.util.rx.AppSchedulerProvider
import com.hunachi.hunachi.githunaclient.util.rx.SchedulerProvider

/**
 * Created by hunachi on 2018/02/20.
 */
val MainApplication.kodeinModules by Kodein.lazy {
    import(modelModules)
    import(viewModelModules)
    /*if you want difference instance of same args fragment, please it make multiton -> factory*/
    bind<MainActivity>() with refSingleton(softReference) { MainActivity() }
    bind<MainProfileActivity>() with provider { MainProfileActivity() }
    bind<LoginGithubActivity>() with provider { LoginGithubActivity() }
    bind<SchedulerProvider>() with refSingleton(softReference) { AppSchedulerProvider() }
    bind<UserInfoFragment>() with factory { userName: String -> UserInfoFragment.newInstance(userName) }
    bind<ListsFragment>() with factory { listsArgument: ListsArgument -> ListsFragment.newInstance(listsArgument) }
    bind<GithubApiRepository>() with multiton { application: MyApplication -> GithubApiRepository(application.token, application.userName) }
    bind<ViewPagerFragment>() with factory { userName: String -> ViewPagerFragment.newInstance(userName) }
    bind<OwnerInfoFragment>() with factory { userName: String -> OwnerInfoFragment.newInstance(userName) }
    bind<LoadingDialogAdapter>() with factory { it: Context -> LoadingDialogAdapter(it) }
    bind<WarningDialogAdapter>() with scopedSingleton(androidContextScope) { WarningDialogAdapter(it) }
}
