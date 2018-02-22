package com.example.hunachi.githunaclient.presentation.application

import android.content.Context
import com.example.hunachi.githunaclient.data.api.responce.User
import com.example.hunachi.githunaclient.data.repository.GithubApiRepository
import com.example.hunachi.githunaclient.data.repository.githubTokenModule
import com.example.hunachi.githunaclient.presentation.dialog.LoadingDialogAdapter
import com.example.hunachi.githunaclient.presentation.dialog.WarningDialogAdapter
import com.example.hunachi.githunaclient.presentation.fragment.viewpager.ViewPagerFragment
import com.example.hunachi.githunaclient.presentation.fragment.feeds.FeedsFragment
import com.example.hunachi.githunaclient.presentation.fragment.feeds.eventViewModelModule
import com.example.hunachi.githunaclient.presentation.fragment.userinfo.UserInfoFragment
import com.example.hunachi.githunaclient.presentation.fragment.userinfo.userInfoViewModelModule
import com.example.hunachi.githunaclient.presentation.fragment.viewpager.adapter.profilePagerAdapterModule
import com.example.hunachi.githunaclient.presentation.fragment.viewpager.viewPagerViewModelModule
import com.example.hunachi.githunaclient.presentation.helper.navigatorModule
import com.example.hunachi.githunaclient.presentation.login.LoginGithubActivity
import com.example.hunachi.githunaclient.presentation.login.loginViewModelModule
import com.example.hunachi.githunaclient.presentation.main.MainActivity
import com.example.hunachi.githunaclient.presentation.main.mainViewModelModule
import com.example.hunachi.githunaclient.presentation.main.profile.MainProfileActivity
import com.example.hunachi.githunaclient.util.rx.AppSchedulerProvider
import com.example.hunachi.githunaclient.util.rx.SchedulerProvider
import com.github.salomonbrys.kodein.*

/**
 * Created by hunachi on 2018/02/20.
 */
val MainApplication.kodeinModules by Kodein.lazy {
    //model
    import(githubTokenModule)
    import(navigatorModule)
    import(profilePagerAdapterModule)
    //view model
    import(eventViewModelModule)
    import(userInfoViewModelModule)
    import(viewPagerViewModelModule)
    import(loginViewModelModule)
    import(mainViewModelModule)
    //view
    bind<MainActivity>() with singleton { MainActivity() }
    bind<MainProfileActivity>() with singleton { MainProfileActivity() }
    bind<LoginGithubActivity>() with singleton { LoginGithubActivity() }
    bind<SchedulerProvider>() with singleton { AppSchedulerProvider() }
    bind<UserInfoFragment>() with multiton { userName: String -> UserInfoFragment.newInstance(userName) }
    bind<FeedsFragment>() with multiton { userName: String -> FeedsFragment.newInstance(userName) }
    bind<GithubApiRepository>() with multiton { application: MyApplication -> GithubApiRepository(application.token) }
    bind<ViewPagerFragment>() with multiton { userName: String -> ViewPagerFragment.newInstance(userName) }
    bind<LoadingDialogAdapter>() with multiton { context: Context -> LoadingDialogAdapter(context) }
    bind<WarningDialogAdapter>() with multiton { context: Context -> WarningDialogAdapter(context) }
}