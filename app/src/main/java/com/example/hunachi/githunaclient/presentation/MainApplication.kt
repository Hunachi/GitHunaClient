package com.example.hunachi.githunaclient.presentation

import android.content.Context
import androidx.content.edit
import com.example.hunachi.githunaclient.data.api.responce.User
import com.example.hunachi.githunaclient.data.repository.GithubApiRepository
import com.example.hunachi.githunaclient.kodein.*
import com.example.hunachi.githunaclient.presentation.fragment.UserInfoFragment
import com.example.hunachi.githunaclient.presentation.fragment.feeds.FeedsFragment
import com.example.hunachi.githunaclient.presentation.login.LoginGithubActivity
import com.example.hunachi.githunaclient.presentation.main.MainActivity
import com.example.hunachi.githunaclient.presentation.main.profile.MainProfileActivity
import com.example.hunachi.githunaclient.util.rx.AppSchedulerProvider
import com.example.hunachi.githunaclient.util.rx.SchedulerProvider
import com.github.salomonbrys.kodein.*

/**
 * Created by hunachi on 2018/01/27.
 */
class MainApplication : MyApplication(), KodeinAware {
    
    override val kodein by Kodein.lazy {
        bind<MyApplication>() with singleton { this@MainApplication as MyApplication }
        import(mainViewModelModule)
        import(loginViewModelModule)
        import(githubTokenModule)
        import(navigatorModule)
        import(userInfoViewModelModule)
        import(followerEventViewModelModule)
        import(profilePagerAdapterModule)
        import(mainProfileViewModelModule)
        bind<MainActivity>() with singleton { MainActivity() }
        bind<MainProfileActivity>() with singleton{ MainProfileActivity() } //TODO make to multiton.
        bind<LoginGithubActivity>() with singleton { LoginGithubActivity() }
        bind<SchedulerProvider>() with singleton { AppSchedulerProvider() }
        bind<UserInfoFragment>() with multiton {user: User -> UserInfoFragment.newInstance(user) }
        bind<FeedsFragment>() with multiton { user: User -> FeedsFragment.newInstance(user) }
        bind<GithubApiRepository>() with multiton { token: String -> GithubApiRepository(token) }
    }
    
    companion object {
        const val userToken = "user_token"
    }
    
    private val preferences by lazy {
        getSharedPreferences("$packageName.txt", Context.MODE_PRIVATE)
    }
    
    override fun onCreate() {
        super.onCreate()
        token = preferences.getString(userToken, "")
    }
    
    override fun setUserToken(token: String) {
        super.setUserToken(token)
        preferences.edit {
            putString(userToken, token)
        }
    }
    
    override fun deleteUserToken() {
        super.deleteUserToken()
        preferences.edit {
            remove(userToken)
        }
    }
}
