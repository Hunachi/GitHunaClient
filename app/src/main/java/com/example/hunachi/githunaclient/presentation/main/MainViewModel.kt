package com.example.hunachi.githunaclient.presentation.main

import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.hunachi.githunaclient.R
import com.example.hunachi.githunaclient.presentation.base.BaseViewModel
import com.example.hunachi.githunaclient.util.BottomNavigationListner
import com.example.hunachi.githunaclient.kodein.MainViewModelModule
import com.example.hunachi.githunaclient.presentation.base.BaseActivity
import com.github.salomonbrys.kodein.*
import com.github.salomonbrys.kodein.android.androidActivityScope

/**
 * Created by hunachi on 2018/01/27.
 */

class MainViewModel(private val module: MainViewModelModule) : BaseViewModel(module.application) {
    
    /*private val textProcessor: PublishProcessor<String> = PublishProcessor.create()
    var text: LiveData<String> = LiveDataReactiveStreams.fromPublisher(textProcessor)*/
    
    private val application = module.application
    private val navigator = module.navigator
    private val fragment = module.userInfoFragment
    
    override fun onCreate() {
        super.onCreate()
        //Log.d("acccess_token", application.user.token)
        if (application.user.token.isBlank()) {
            navigator.navigateToLogin()
            Toast.makeText(context, "Github accountと未連携", Toast.LENGTH_SHORT).show()
        }
        else Toast.makeText(context, "今日も一日がんばるぞい!{name}さん!", Toast.LENGTH_SHORT).show()
    }
    
    //Listener of BottomNavigation(what I made hard.)
    fun onItemSelected(): BottomNavigationListner = BottomNavigationListner {
        item -> Toast.makeText(context, "${item.itemId}", Toast.LENGTH_SHORT).show()
        if (item.itemId == R.id.action_search){
            navigator.activity.replaceFragment(fragment, R.id.container)
        }
        true
    }
    
}

val mainViewModelModule = Kodein.Module {
    bind<MainViewModel>() with scopedSingleton(androidActivityScope) {
        MainViewModel(
            MainViewModelModule(
                navigator = with(it as AppCompatActivity).instance(),
                application = instance(),
                userInfoFragment = instance()
            )
        )
    }
}