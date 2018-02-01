package com.example.hunachi.githunaclient.presentation.main

import android.widget.Toast
import com.example.hunachi.githunaclient.presentation.base.BaseViewModel
import com.example.hunachi.githunaclient.util.BottomNavigationListner
import com.example.hunachi.githunaclient.util.models.MainViewModels
import com.github.salomonbrys.kodein.*
import com.github.salomonbrys.kodein.android.androidActivityScope

/**
 * Created by hunachi on 2018/01/27.
 */

class MainViewModel(private val models: MainViewModels) : BaseViewModel(models.application) {
    
    /*private val textProcessor: PublishProcessor<String> = PublishProcessor.create()
    var text: LiveData<String> = LiveDataReactiveStreams.fromPublisher(textProcessor)*/
    
    private val application = models.application
    private val navigator = models.navigator
    
    override fun onCreate() {
        super.onCreate()
        if (application.user.token.isBlank()) {
            navigator.navigateToLogin()
            Toast.makeText(context, "Github accountと未連携", Toast.LENGTH_SHORT).show()
        }
        Toast.makeText(context, "今日も一日がんばるぞい!{name}さん!", Toast.LENGTH_SHORT).show()
    }
    
    //Listener of BottomNavigation(what I made hard.)
    fun onItemSelected(): BottomNavigationListner = BottomNavigationListner {
        item -> Toast.makeText(context, "${item.itemId}", Toast.LENGTH_SHORT).show()
        true
    }
    
}

val mainViewModelModule = Kodein.Module {
    bind<MainViewModel>() with scopedSingleton(androidActivityScope) {
        MainViewModel(MainViewModels(navigator = with(it as MainActivity).instance(), application = instance()))
    }
}