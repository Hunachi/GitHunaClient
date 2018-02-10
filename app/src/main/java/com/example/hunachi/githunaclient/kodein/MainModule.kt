package com.example.hunachi.githunaclient.kodein

import com.example.hunachi.githunaclient.data.repository.GithubApiRepository
import com.example.hunachi.githunaclient.presentation.MainApplication
import com.example.hunachi.githunaclient.presentation.MyApplication
import com.example.hunachi.githunaclient.presentation.fragment.UserInfoFragment
import com.example.hunachi.githunaclient.presentation.fragment.event.FollowerEventFragment
import com.example.hunachi.githunaclient.presentation.login.LoginGithubActivity
import com.example.hunachi.githunaclient.presentation.main.MainActivity
import com.example.hunachi.githunaclient.util.rx.AppSchedulerProvider
import com.example.hunachi.githunaclient.util.rx.SchedulerProvider
import com.github.salomonbrys.kodein.*

/**
 * Created by hunachi on 2018/02/10.
 */
val mainModule = Kodein.Module{
    import(mainViewModelModule)
    import(loginViewModelModule)
    import(githubTokenModule)
    import(navigatorModule)
    import(userInfoViewModelModule)
    import(followerEventViewModelModule)
    import(profilePagerAdapterModule)
    bind<MyApplication>() with singleton { MainApplication as MyApplication }
    bind<MainActivity>() with singleton { MainActivity() }
    bind<LoginGithubActivity>() with singleton { LoginGithubActivity() }
    bind<SchedulerProvider>() with singleton { AppSchedulerProvider() }
    bind<UserInfoFragment>() with singleton { UserInfoFragment.newInstance() }
    bind<FollowerEventFragment>() with singleton { FollowerEventFragment.newInstance() }
    bind<GithubApiRepository>() with factory { token: String -> GithubApiRepository(instance(), token) }
}