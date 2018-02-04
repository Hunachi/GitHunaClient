package com.example.hunachi.githunaclient.presentation.main

import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.hunachi.githunaclient.R
import com.example.hunachi.githunaclient.presentation.base.BaseViewModel
import com.example.hunachi.githunaclient.util.BottomNavigationListner
import com.example.hunachi.githunaclient.presentation.MainApplication
import com.example.hunachi.githunaclient.presentation.MyApplication
import com.example.hunachi.githunaclient.presentation.event.UserInfoFragment
import com.example.hunachi.githunaclient.presentation.helper.Navigator
import com.github.salomonbrys.kodein.*
import com.github.salomonbrys.kodein.android.androidActivityScope

/**
 * Created by hunachi on 2018/01/27.
 */

class MainViewModel(
        val navigator: Navigator,
        val application: MyApplication,
        val userInfoFragment: UserInfoFragment
) : BaseViewModel(application) {
    
    /*private val textProcessor: PublishProcessor<String> = PublishProcessor.create()
    var text: LiveData<String> = LiveDataReactiveStreams.fromPublisher(textProcessor)*/
    
    override fun onCreate() {
        super.onCreate()
        application.deleteUserToken()
    }
    
    override fun onStart() {
        super.onStart()
        if (application.token.isBlank()) {
            navigator.navigateToLogin()
            Toast.makeText(context, "Github accountと未連携", Toast.LENGTH_SHORT).show()
        } else Toast.makeText(context, "今日も一日がんばるぞい!{name}さん!", Toast.LENGTH_SHORT).show()
    }
    
    //Listener of BottomNavigation(what I made hard.)
    fun onItemSelected(): BottomNavigationListner = BottomNavigationListner { item ->
        Toast.makeText(context, "${item.itemId}", Toast.LENGTH_SHORT).show()
        when (item.itemId) {
            R.id.action_search -> navigator.replaceFragment(userInfoFragment)
        }
        true
    }
}

val mainViewModelModule = Kodein.Module {
    bind<MainViewModel>() with scopedSingleton(androidActivityScope) {
        MainViewModel(
            navigator = with(it as AppCompatActivity).instance(),
            application = instance(),
            userInfoFragment = instance()
        )
    }
}